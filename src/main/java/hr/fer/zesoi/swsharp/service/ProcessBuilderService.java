package hr.fer.zesoi.swsharp.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ProcessBuilderService {

	private static final Logger logger = LoggerFactory
			.getLogger(ProcessBuilderService.class);

	public synchronized void runCommand(List<String> command) {
		Process process;
		String line;
		try {
			process = new ProcessBuilder(command).start();
			BufferedReader err = new BufferedReader(new InputStreamReader(
					process.getErrorStream()));
			while ((line = err.readLine()) != null) {
				System.err.println(line);
				logger.info(line);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			// return "IOexception";
		}

		System.out.println("process finished");
	}
}
