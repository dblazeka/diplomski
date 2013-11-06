package hr.fer.zesoi.swsharp.controllers;

import hr.fer.zesoi.swsharp.modules.Module;
import hr.fer.zesoi.swsharp.modules.ModuleContainer;
import hr.fer.zesoi.swsharp.parameters.ModuleParameter;
import hr.fer.zesoi.swsharp.parameters.ParameterContainer;
import hr.fer.zesoi.swsharp.parameters.TextAndFileParameter;
import hr.fer.zesoi.swsharp.service.EmailService;
import hr.fer.zesoi.swsharp.service.ModuleExecutor;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Controller
public class SubmitFormController {

	@Autowired
	private ModuleContainer moduleContainer;

	@Autowired
	private ModuleExecutor moduleExecutor;

	@Autowired
	private EmailService emailService;

	@RequestMapping(value = "/{moduleName}/submit", method = RequestMethod.POST)
	public String submit(Model model, @PathVariable String moduleName,
			MultipartHttpServletRequest req) {
		model.addAttribute("allModules", moduleContainer.getModules());

		System.out.println(moduleName);
		Module module = moduleContainer.getModule(moduleName);
		bindParameters(req, module);
		moduleExecutor.prepare(module);
		if (module.getComplementOption()
				&& req.getParameterValues("complement") != null) {
			module.setPath(module.getComplementPath());
		}
		if (module.getUsesDatabase()
				&& req.getParameterValues("database") != null) {
			String database = (String) req.getParameterValues("database")[0];
			System.out.println("database " + database);
			if (!database.equals("none")) {
				module.getParameterContainer()
						.getModuleParameter("swsharpdbj")
						.setCommandArgument(
								new File(module.getDatabaseContainer()
										.getDatabase(database).getPath()));
			}
		}
		System.out.println(module.getJobDirectory().getAbsolutePath());
		model.addAttribute("jobId", module.getJobDirectory().getName());
		model.addAttribute("usrtmp", System.getProperty("java.io.tmpdir"));
		try {
			moduleExecutor.execute(module);
			sendEmailIfNecessary(req, module.getJobDirectory().getName());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "progress";
	}

	private void sendEmailIfNecessary(MultipartHttpServletRequest req,
			String resultsUrl) {
		if (req.getParameterValues("sendEmail") != null) {
			if (req.getParameterValues("sendEmail")[0].equals("send")) {
				emailService.sendEmail(req.getParameterValues("email")[0],
						resultsUrl);
			}
		}

	}

	private void bindParameters(MultipartHttpServletRequest req, Module module) {
		ParameterContainer parameterContainer = module.getParameterContainer();
		ModuleParameter moduleParameter;
		Map<String, String[]> parameters = req.getParameterMap();
		fillTextAndFileParams(parameters, module, req.getFileMap());
		parameters = removeTextFileParams(parameters, module);
		for (String key : parameters.keySet()) {
			moduleParameter = parameterContainer.getModuleParameter(key);
			if (moduleParameter != null) {
				moduleParameter.setValue(parameters.get(key)[0]);
			}
		}
		Map<String, MultipartFile> files = req.getFileMap();
		for (String key : files.keySet()) {
			moduleParameter = parameterContainer.getModuleParameter(key);
			if (moduleParameter != null) {
				moduleParameter.setValue(files.get(key));
			}
		}
	}

	private Map<String, String[]> removeTextFileParams(
			Map<String, String[]> parameters, Module module) {
		ParameterContainer parameterContainer = module.getParameterContainer();
		List<String> keysToRemove = new ArrayList<String>();
		// TODO Auto-generated method stub
		for (String key : parameters.keySet()) {
			for (ModuleParameter parameter : parameterContainer.getParameters()) {
				if (parameter instanceof TextAndFileParameter) {
					TextAndFileParameter textAndFileParameter = (TextAndFileParameter) parameter;
					if (key.equals(textAndFileParameter.getName()
							+ textAndFileParameter.getTextSuffix())) {
						keysToRemove.add(key);
					}
				}
			}
		}
		for (String keyToRemove : keysToRemove) {
			parameters.remove(keyToRemove);
		}
		return parameters;
	}

	private void fillTextAndFileParams(Map<String, String[]> parameters,
			Module module, Map<String, MultipartFile> files) {
		// TODO Auto-generated method stub
		ParameterContainer parameterContainer = module.getParameterContainer();
		for (ModuleParameter parameter : parameterContainer.getParameters()) {
			if (parameter instanceof TextAndFileParameter) {
				TextAndFileParameter textAndFileParameter = (TextAndFileParameter) parameter;
				if (parameters.get(textAndFileParameter.getName()
						+ textAndFileParameter.getTextSuffix())[0] != null
						&& !(parameters.get(textAndFileParameter.getName()
								+ textAndFileParameter.getTextSuffix())[0]
								.isEmpty())) {
					textAndFileParameter.setValue(parameters
							.get(textAndFileParameter.getName()
									+ textAndFileParameter.getTextSuffix())[0]);
				} else {
					textAndFileParameter.setValue(files
							.get(textAndFileParameter.getName()
									+ textAndFileParameter.getFileSuffix()));
				}
			}
		}
	}
}
