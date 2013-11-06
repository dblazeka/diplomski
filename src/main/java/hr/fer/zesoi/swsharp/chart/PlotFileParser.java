package hr.fer.zesoi.swsharp.chart;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class PlotFileParser {
	public List<Point> parsePoints(File resultDir) throws IOException {
		File plotFile = new File(resultDir.getAbsolutePath() + "/plot");
		List<Point> result = new ArrayList<Point>();
		if(!plotFile.exists()){
			return result;
		}
		BufferedReader br = new BufferedReader(new FileReader(plotFile));
		String line;
		String[] split;
		while ((line = br.readLine()) != null) {
			if (line.isEmpty())
				continue;
			if (line.trim().matches("^([0-9]+)\\s([0-9]+)$")) {
				split = line.split(" ");
				result.add(new Point(Integer.parseInt(split[0]), Integer
						.parseInt(split[0])));
			}
		}
		br.close();

		return result;

	}
}
