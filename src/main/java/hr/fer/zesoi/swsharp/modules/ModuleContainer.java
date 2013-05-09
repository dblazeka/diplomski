package hr.fer.zesoi.swsharp.modules;

import java.util.List;

public class ModuleContainer {
	private List<Module> modules;

	public List<Module> getModules() {
		return modules;
	}

	public void setModules(List<Module> modules) {
		this.modules = modules;
	}

	public Module getModule(String name){
		for(Module module : modules){
			if(module.getName().equals(name)){
				return module;
			}
		}
		return null;
	}

	public void setNoneActive(){
		for(Module module : modules){
			module.setIsActive(false);
		}
	}
}
