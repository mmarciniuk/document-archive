package pl.mm.documentArchive.webApp.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(BaseViewController.RequestMappings.BASE)
public class NavbarViewController extends BaseViewController {

	@GetMapping(value = {RequestMappings.BASE, RequestMappings.INDEX, RequestMappings.MAIN_PAGE})
	public String index() {
		return "index";
	}

	@GetMapping(value = {RequestMappings.ABOUT})
	public String about() {
		return "about";
	}

}
