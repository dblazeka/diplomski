package hr.fer.zesoi.swsharp.modules;

import hr.fer.zesoi.swsharp.databases.Database;
import hr.fer.zesoi.swsharp.databases.DatabaseContainer;
import hr.fer.zesoi.swsharp.parameters.ParameterContainer;
import hr.fer.zesoi.swsharp.service.CommandBuilder;

import java.io.File;
import java.util.List;

public class Module {

	private String displayName;

	private String name;

	private String path;

	private File jobDirectory;

	private boolean isActive;

	private ParameterContainer parameterContainer;

	private boolean usesDumpFile = true;

	private boolean complementOption = false;

	private String complementPath;

	private DatabaseContainer databaseContainer;
	private boolean usesDatabase = false;

	public List<Database> getDatabases(){
		return databaseContainer.getDatabases();
	}


	public DatabaseContainer getDatabaseContainer() {
		return databaseContainer;
	}

	public void setDatabaseContainer(DatabaseContainer databaseContainer) {
		this.databaseContainer = databaseContainer;
	}

	public boolean getUsesDatabase() {
		return usesDatabase;
	}

	public void setUsesDatabase(boolean usesDatabase) {
		this.usesDatabase = usesDatabase;
	}
	public String getComplementPath() {
		return complementPath;
	}

	public void setComplementPath(String complementPath) {
		this.complementPath = complementPath;
	}

	public boolean getComplementOption() {
		return complementOption;
	}

	public void setComplementOption(boolean complementOption) {
		this.complementOption = complementOption;
	}

	public boolean usesDumpFile() {
		return usesDumpFile;
	}

	public void setUsesDumpFile(boolean usesDumpFile) {
		this.usesDumpFile = usesDumpFile;
	}

	public File getJobDirectory() {
		return jobDirectory;
	}

	public void setJobDirectory(File jobDirectory) {
		this.jobDirectory = jobDirectory;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public ParameterContainer getParameterContainer() {
		return parameterContainer;
	}

	public void setParameterContainer(ParameterContainer parameterContainer) {
		this.parameterContainer = parameterContainer;
	}

	public List<String> getCommand(){
		return CommandBuilder.buildCommand(this);
	}

}
