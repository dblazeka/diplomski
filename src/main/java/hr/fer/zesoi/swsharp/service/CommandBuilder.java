package hr.fer.zesoi.swsharp.service;

import hr.fer.zesoi.swsharp.modules.Module;
import hr.fer.zesoi.swsharp.parameters.ModuleParameter;
import hr.fer.zesoi.swsharp.parameters.ParameterContainer;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class CommandBuilder {
	public static List<String> buildCommand(Module module) {
		List<String> result = new ArrayList<String>();
		result.add(module.getPath());
		ParameterContainer parameterContainer = module.getParameterContainer();
		List<ModuleParameter> parameters = parameterContainer.getParameters();
		for (ModuleParameter parameter : parameters) {
			result.add(parameter.getCommandArgumentName());
			result.add(parameter.getCommandArgument());
		}

		return result;
	}

	public static List<String> setOutputLocation(List<String> command, String location){
		command.add("--out");
		command.add(location);
		return command;
	}

	public static List<String> setOutputFormat(List<String> command, String format){
		command.add("--outfmt");
		command.add(format);
		return command;
	}
}
