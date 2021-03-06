package hr.fer.zesoi.swsharp.parameters;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.multipart.MultipartFile;

public class FileParameter implements ModuleParameter {
	/**
	 * serves as an id
	 */
	private String name;

	@Autowired
	@Qualifier("inputDirectory")
	private File inputDirectory;

	private String displayName;
	private String type = "file";
	private String commandArgumentName;
	private File file;
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
		return file.getAbsolutePath();
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public void setCommandArgumentName(String commandArgumentName) {
		this.commandArgumentName = commandArgumentName;
	}

	@Override
	public void setValue(Object value) {
		if (!(value instanceof MultipartFile)) {
			throw new IllegalArgumentException("Argument should be a file");
		} else {

			MultipartFile multipartFile = (MultipartFile) value;
			try {
				file = File.createTempFile("swsharp", null, inputDirectory);
				System.out.println(file.getAbsolutePath());
				multipartFile.transferTo(file);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	@Override
	public String getDefaultValue() {
		// TODO intentionally blank
		return null;
	}

	@Override
	public void setDefaultValue(String value) {
		// TODO intentionally blank

	}

	@Override
	public void setCommandArgument(File file) {
		// TODO Auto-generated method stub

	}



}
