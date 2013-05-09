package hr.fer.zesoi.swsharp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class DownloadController {
	@RequestMapping(value = "/download", method = RequestMethod.GET)
	public void home(@RequestParam("fileName") String fileName,
			HttpServletResponse response) {
//		File file = new File("/home/data/webinputs/" + fileName);
		File file = new File("C:/development/probica.txt");
		if(!file.exists()){
			try {
				response.sendError(404, "File not found");
				return;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		response.setContentType("text/plain;charset=UTF-8");

		response.setContentLength((int) file.getTotalSpace());

		response.setHeader("Content-Disposition", "attachment; filename=\""
				+ file.getName() + ".txt\"");

		try {
			IOUtils.copy(new FileInputStream(file), response.getOutputStream());
			response.flushBuffer();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
