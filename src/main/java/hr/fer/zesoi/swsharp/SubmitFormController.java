package hr.fer.zesoi.swsharp;

import hr.fer.zesoi.swsharp.modules.Module;
import hr.fer.zesoi.swsharp.modules.ModuleContainer;
import hr.fer.zesoi.swsharp.parameters.ModuleParameter;
import hr.fer.zesoi.swsharp.parameters.ParameterContainer;
import hr.fer.zesoi.swsharp.service.ModuleExecutor;

import java.io.IOException;
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

	@RequestMapping(value = "/{moduleName}/submit", method = RequestMethod.POST)
	public String submit(Model model, @PathVariable String moduleName,
			MultipartHttpServletRequest req) {
		model.addAttribute("allModules", moduleContainer.getModules());

		System.out.println(moduleName);
		Module module = moduleContainer.getModule(moduleName);
		bindParameters(req, module);
		moduleExecutor.prepare(module);
		System.out.println(module.getJobDirectory().getAbsolutePath());
		model.addAttribute("jobId", module.getJobDirectory().getName());
		model.addAttribute("usrtmp", System.getProperty("java.io.tmpdir"));
		try {
			moduleExecutor.execute(module);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "progress";
	}

	private void bindParameters(MultipartHttpServletRequest req, Module module) {
		ParameterContainer parameterContainer = module.getParameterContainer();
		ModuleParameter moduleParameter;
		Map<String, String[]> parameters = req.getParameterMap();
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
}
