package hr.fer.zesoi.swsharp.service;

import hr.fer.zesoi.swsharp.modules.Module;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.io.Files;

@Service
public class ModuleExecutor {

	@Autowired
	private ProcessBuilderService processBuilderService;

	public void execute(Module module) throws IOException {
		File dir = module.getJobDirectory();
		if (dir == null) {
			throw new IllegalStateException(
					"Module has to be prepared before execution");
		}
		List<String> command = module.getCommand();

		if (module.usesDumpFile()) {
			File outputFile = new File(dir, "dump");
			CommandBuilder.setOutputLocation(command,
					outputFile.getAbsolutePath());
			CommandBuilder.setOutputFormat(command, "dump");

			processBuilderService.runCommand(command);
			createPairStatFile(dir, outputFile);
			createPlotFile(dir, outputFile);
			System.out.println("finished");
		} else {
			File outputFile = new File(dir, "pairstat");
			CommandBuilder.setOutputLocation(command,
					outputFile.getAbsolutePath());
			for(String s : command){
				System.out.println(s);
			}
			processBuilderService.runCommand(command);
			System.out.println("finished");
		}

		// indicates finished
		File finished = new File(dir.getAbsolutePath(), "finished");
		System.out.println(finished.getAbsolutePath());
		System.out.println(finished.createNewFile());
	}

	private void createPlotFile(File dir, File outputFile) {
		File plotFile = new File(dir, "plot");
		List<String> command = new ArrayList<String>();
		command.add("swsharpout");
		command.add("-i");
		command.add(outputFile.getAbsolutePath());
		command.add("--outfmt");
		command.add("plot");
		command.add("--out");
		command.add(plotFile.getAbsolutePath());
		processBuilderService.runCommand(command);
	}

	private void createPairStatFile(File dir, File outputFile) {
		File pairStatFile = new File(dir, "pairstat");
		List<String> command = new ArrayList<String>();
		command.add("swsharpout");
		command.add("-i");
		command.add(outputFile.getAbsolutePath());
		command.add("--outfmt");
		command.add("pair-stat");
		command.add("--out");
		command.add(pairStatFile.getAbsolutePath());
		processBuilderService.runCommand(command);
	}

	public Module prepare(Module module) {
		File dir = Files.createTempDir();
		System.out.println(dir.getAbsolutePath());
		module.setJobDirectory(dir);
		return module;
	}

}
