package hr.fer.zesoi.swsharp;

import hr.fer.zesoi.swsharp.chart.PlotFileParser;
import hr.fer.zesoi.swsharp.chart.Point;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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


	@RequestMapping(value = "/results/{jobId}", method = RequestMethod.GET)
	public void submit(Model model, @PathVariable String jobId,
			HttpServletRequest req, HttpServletResponse res) {
		File resultsDir = new File(System.getProperty("java.io.tmpdir") + "/" + jobId);
		File finished = new File(resultsDir.getAbsolutePath() + "/" + "finished");
		if(!resultsDir.exists() || !finished.exists()){
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
//			List<Point> points= new ArrayList<Point>();
//			points.add(new Point(1, 2));
//			points.add(new Point(2, 3));
//			model.addAttribute("points", points);

			model.addAttribute("points", plotFileParser.parsePoints(new File(System.getProperty("java.io.tmpdir") + "/" + jobId)));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "flottest";
	}
}
