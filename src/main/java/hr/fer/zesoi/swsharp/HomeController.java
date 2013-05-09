package hr.fer.zesoi.swsharp;

import java.util.ArrayList;
import java.util.List;

import hr.fer.zesoi.swsharp.modules.Module;
import hr.fer.zesoi.swsharp.modules.ModuleContainer;
import hr.fer.zesoi.swsharp.parameters.ParameterContainer;
import hr.fer.zesoi.swsharp.service.ProcessBuilderService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory
			.getLogger(HomeController.class);

	@Autowired
	private ModuleContainer moduleContainer;

	@Autowired
	private ProcessBuilderService processBuilderService;

	@RequestMapping(value = "/flot", method=RequestMethod.GET)
	public String flot(){

		return "flottest";
	}

	@RequestMapping(value = "/test", method=RequestMethod.GET)
	public String test(){
		final List<String> command = new ArrayList<String>();
		command.add("swsharpn");
		command.add("-i");
		command.add("/home/data/NC_000898.1");
		command.add("-j");
		command.add("/home/data/NC_007605.1");
		command.add("--out");
		command.add("/var/tmp/tomcat7/out");
		System.out.println("/var/tmp/tomcat7/out");
		Thread th = new Thread(new Runnable() {

			@Override
			public void run() {
				processBuilderService.runCommand(command);
			}
		});
		th.start();
		return "home";
	}

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model) {
		return generateForm(model, moduleContainer.getModules().get(0)
				.getName());
	}

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/{moduleName}", method = RequestMethod.GET)
	public String generateForm(Model model, @PathVariable() String moduleName) {
		logger.info("Welcome home! The client locale is {}.", "bla");

		// set active module
		moduleContainer.setNoneActive();
		Module module = moduleContainer.getModule(moduleName);
		if (module != null) {
			module.setIsActive(true);
			model.addAttribute("module", module);
			model.addAttribute("moduleParameters", module.getParameterContainer().getParameters());
		} else {
			return "error";
		}

		model.addAttribute("allModules", moduleContainer.getModules());

		return "home";
	}

}
