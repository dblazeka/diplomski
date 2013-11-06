package hr.fer.zesoi.swsharp.controllers;

import hr.fer.zesoi.swsharp.chart.PlotFileParser;
import hr.fer.zesoi.swsharp.chart.Point;
import hr.fer.zesoi.swsharp.modules.ModuleContainer;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ResultsController {
	@Autowired
	private PlotFileParser plotFileParser;

	@Autowired
	private ModuleContainer moduleContainer;

	@RequestMapping(value = "/results/{jobId}", method = RequestMethod.GET)
	public void submit(Model model, @PathVariable String jobId,
			HttpServletRequest req, HttpServletResponse res) {
		File resultsDir = new File(System.getProperty("java.io.tmpdir") + "/"
				+ jobId);
		File finished = new File(resultsDir.getAbsolutePath() + "/"
				+ "finished");
		if (!resultsDir.exists() || !finished.exists()) {
			try {
				res.sendError(HttpServletResponse.SC_NOT_FOUND);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@RequestMapping(value = "/results/{jobId}/show", method = RequestMethod.GET)
	public String showResults(Model model, @PathVariable String jobId,
			HttpServletRequest req, HttpServletResponse res) {
		try {
			// List<Point> points= new ArrayList<Point>();
			// points.add(new Point(1, 2));
			// points.add(new Point(2, 3));
			// model.addAttribute("points", points);
			List<Point> points = plotFileParser.parsePoints(new File(System
					.getProperty("java.io.tmpdir") + "/" + jobId));
			String result = FileUtils.readFileToString(
					new File(System.getProperty("java.io.tmpdir") + "/" + jobId
							+ "/pairstat"), "UTF-8");
			if (points.isEmpty()) {
				model.addAttribute("drawchart", false);
				result = FileUtils.readFileToString(
						new File(System.getProperty("java.io.tmpdir") + "/"
								+ jobId + "/pairstat"), "UTF-8");
				result = showResultAsTable(result);
			} else {
				result = postProcessPairStat(result);
				model.addAttribute("drawchart", true);
			}
			model.addAttribute("jobId", jobId);
			model.addAttribute("points", points);
			model.addAttribute("pairstat", result);
			model.addAttribute("allModules", moduleContainer.getModules());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "flottest";
	}

	private String postProcessPairStat(String result) {
		StringBuilder sb = new StringBuilder();
		String[] lines = result.split("\n");
		String[] split;
		int alignmentStart = -1;
		String line;
		char[] chars = null;
		for (int i = 0; i < lines.length; i++) {
			line = lines[i];
			System.out.println("Processing line " + i + " " + line);
			if (line.startsWith("##") && i != 0) {
				System.out.println("if1");
				alignmentStart = i;
				sb.append(line);
				sb.append("<br>");
				sb.append("<table class=\"table table-condensed\">");
				sb.append("<tbody>");
			} else if (i > alignmentStart && alignmentStart != -1) {
				System.out.println("if2");
				if (line.isEmpty()) {
					System.out.println("continue");
					continue;
				}
				split = line.split("\\s+");
				sb.append("<tr>");
				if (split.length == 4) {
					System.out.println("4 clana");
					sb.append("<td>");
					sb.append(split[0]);
					sb.append("</td>");
					sb.append("<td>");
					sb.append(split[1]);
					sb.append("</td>");
					for(char c : split[2].toCharArray()){
						sb.append("<td>");
						sb.append(c);
						sb.append("</td>");
					}
					sb.append("<td>");
					sb.append(split[3]);
					sb.append("</td>");
				} else {
					System.out.println("mali split size " + split.length);
					sb.append("<td>");
					sb.append("</td>");
					sb.append("<td>");
					sb.append("</td>");
					for (char c : split[1].toCharArray()) {
						sb.append("<td>");
						sb.append(c);
						sb.append("</td>");
					}
					sb.append("<td>");
					sb.append("</td>");
				}
				sb.append("</tr>");
			} else {
				System.out.println("else");
				sb.append(line);
				sb.append("<br>");
			}
		}
		sb.append("</tbody>");
		sb.append("</table>");
		return sb.toString();
	}

	private String showResultAsTable(String result) {
		StringBuilder sb = new StringBuilder();
		String[] lines = result.split("\n");
		String[] split;
		for (String line : lines) {
			if (line.startsWith("#")) {
				sb.append(line);
				sb.append("<br>");
				sb.append("<table class=\"table table-hover table-condensed\">");
			} else if (line.contains(",")) {
				split = line.split(",");
				sb.append("<thead>");
				sb.append("<tr>");
				for (String s : split) {
					sb.append("<th>");
					sb.append(s);
					sb.append("</th>");
				}
				sb.append("</tr>");
				sb.append("</thead>");
				sb.append("<tbody>");
			} else if (line.contains("\t")) {
				split = line.split("\t");
				sb.append("<tr>");
				for (String s : split) {
					sb.append("<td>");
					sb.append(s);
					sb.append("</td>");
				}
				sb.append("</tr>");
			}
		}
		sb.append("</tbody>");
		sb.append("</table>");
		return sb.toString();
	}
}
