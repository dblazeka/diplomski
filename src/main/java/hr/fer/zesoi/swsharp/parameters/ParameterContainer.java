package hr.fer.zesoi.swsharp.parameters;

import java.util.List;

public class ParameterContainer {
	private List<ModuleParameter> parameters;

	public List<ModuleParameter> getParameters() {
		return parameters;
	}

	public void setParameters(List<ModuleParameter> parameters) {
		this.parameters = parameters;
	}

	public ModuleParameter getModuleParameter(String name) {
		for (ModuleParameter parameter : parameters) {
			if (parameter.getName().equals(name)) {
				return parameter;
			}
		}
		return null;
	}

}
