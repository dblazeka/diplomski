package hr.fer.zesoi.swsharp.parameters;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.multipart.MultipartFile;

public class TextAndFileParameter implements ModuleParameter {
	/**
	 * serves as an id
	 */
	private String name;

	@Autowired
	@Qualifier("inputDirectory")
	private File inputDirectory;

	private String displayName;
	private String type = "textfile";
	private String commandArgumentName;
	private File file;
	private boolean hidden = false;
	private String textSuffix = "text";
	private String fileSuffix = "file";
	private String tooltipText;
	private String defaultValue;

	public String getTooltipText() {
		return tooltipText;
	}

	public void setTooltipText(String tooltipText) {
		this.tooltipText = tooltipText;
	}

	public String getTextSuffix() {
		return textSuffix;
	}

	public void setTextSuffix(String textSuffix) {
		this.textSuffix = textSuffix;
	}

	public String getFileSuffix() {
		return fileSuffix;
	}

	public void setFileSuffix(String fileSuffix) {
		this.fileSuffix = fileSuffix;
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
	@Override
	public void setCommandArgument(File file) {
		this.file = file;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public void setCommandArgumentName(String commandArgumentName) {
		this.commandArgumentName = commandArgumentName;
	}

	@Override
	public void setValue(Object value) {
		if (value instanceof MultipartFile) {

			MultipartFile multipartFile = (MultipartFile) value;
			try {
				file = File.createTempFile("swsharp", null, inputDirectory);
				System.out.println(file.getAbsolutePath());
				multipartFile.transferTo(file);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if (value instanceof String) {
			try {
				file = File.createTempFile("swsharp", null, inputDirectory);
				System.out.println("Text je " + value);
				System.out.println(file.getAbsolutePath());
				FileUtils.writeStringToFile(file, (String) value);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			throw new IllegalArgumentException(
					"Argument should be a file or a string.");
		}

	}

	@Override
	public String getDefaultValue() {
		// TODO intentionally blank
		return defaultValue;
	}

	@Override
	public void setDefaultValue(String value) {
		this.defaultValue = value;

	}

}
