package hr.fer.zesoi.swsharp.parameters;

public class TextParameter implements ModuleParameter {
	/**
	 * serves as an id
	 */
	private String name;

	private String displayName;
	private String type = "text";
	private String commandArgumentName;
	private String commandArgument;

	@Override
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getType() {
		return type;
	}

	@Override
	public String getDisplayName() {
		return displayName;
	}

	@Override
	public String getCommandArgumentName() {
		return commandArgumentName;
	}

	@Override
	public String getCommandArgument() {
		return commandArgument;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public void setCommandArgumentName(String commandArgumentName) {
		this.commandArgumentName = commandArgumentName;
	}

	public void setCommandArgument(String commandArgument) {
		this.commandArgument = commandArgument;
	}

	@Override
	public void setValue(Object value) {
		// TODO Auto-generated method stub
		if(!(value instanceof String)){
			throw new IllegalArgumentException("Object passed should be a string");
		}else{
			this.commandArgument = (String) value;
		}
	}



}
