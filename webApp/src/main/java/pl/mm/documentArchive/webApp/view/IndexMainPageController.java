package pl.mm.documentArchive.webApp.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexMainPageController {

	public static class RequestMappings {
		public static final String BASE = "/";
		public static final String INDEX = "/index";
	}

	@GetMapping(value = {RequestMappings.BASE, RequestMappings.INDEX})
	public String index() {
		return "index";
	}

}
