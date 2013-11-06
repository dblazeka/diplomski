package hr.fer.zesoi.swsharp.databases;

import java.util.List;

public class DatabaseContainer {
	private List<Database> databases;

	public List<Database> getDatabases() {
		return databases;
	}

	public void setDatabases(List<Database> databases) {
		this.databases = databases;
	}

	public Database getDatabase(String name){
		for(Database module : databases){
			if(module.getName().equals(name)){
				return module;
			}
		}
		return null;
	}


}
