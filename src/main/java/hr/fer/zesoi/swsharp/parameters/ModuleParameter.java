package hr.fer.zesoi.swsharp.parameters;

public interface ModuleParameter {
	public String getName();
	public String getType();
	public String getDisplayName();
	public String getCommandArgumentName();
	public String getCommandArgument();
	public void setValue(Object value);
}
