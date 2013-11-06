package hr.fer.zesoi.swsharp.parameters;

import java.io.File;

public class SelectParameter implements ModuleParameter {
	/**
	 * serves as an id
	 */
	private String name;

	private String displayName;
	private String type = "select";
	private String commandArgumentName;
	private String commandArgument;
	private String[] options;
	private String defaultValue;
	private boolean hidden = false;
	private String tooltipText;

	public String getTooltipText() {
		return tooltipText;
	}

	public void setTooltipText(String tooltipText) {
		this.tooltipText = tooltipText;
	}

	public boolean getHidden() {
		return hidden;
	}

	public void setHidden(boolean hidden) {
		this.hidden = hidden;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	public String[] getOptions() {
		return options;
	}

	public void setOptions(String[] options) {
		this.options = options;
	}

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
		if (!(value instanceof String)) {
			throw new IllegalArgumentException(
					"Object passed should be a string");
		} else {
			String stringValue = (String) value;
			if (stringValue.equals("Semi-global")) {
				this.commandArgument = "HW";
			} else {
				this.commandArgument = stringValue;
			}
		}
	}

	@Override
	public void setCommandArgument(File file) {
		// TODO Auto-generated method stub

	}

}
