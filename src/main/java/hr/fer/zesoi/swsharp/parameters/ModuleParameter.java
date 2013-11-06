package hr.fer.zesoi.swsharp.parameters;

import java.io.File;

public interface ModuleParameter {
	public String getName();
	public String getType();
	public String getDisplayName();
	public String getCommandArgumentName();
	public String getCommandArgument();
	public void setValue(Object value);
	public String getDefaultValue();
	public void setDefaultValue(String value);
	public boolean getHidden();
	public void setHidden(boolean hidden);
	public String getTooltipText();
	public void setTooltipText(String text);
	void setCommandArgument(File file);
}
