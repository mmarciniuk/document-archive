package pl.mm.documentArchive.webApp.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import static pl.mm.documentArchive.webApp.view.BaseViewController.AttributeNameForModels.*;

@Controller
@RequestMapping(BaseViewController.RequestMappings.BASE)
public class NavbarViewController extends BaseViewController {

	@GetMapping(value = {RequestMappings.BASE, RequestMappings.INDEX, RequestMappings.MAIN_PAGE})
	public ModelAndView index() {
		ModelAndView modelAndView = new ModelAndView(MAIN_VIEW_NAME);
		modelAndView.addObject(MAIN_PAGE.getName(), MAIN_PAGE.getValue());

		return modelAndView;
	}

	@GetMapping(value = {RequestMappings.ABOUT})
	public ModelAndView about() {
		ModelAndView modelAndView = new ModelAndView(MAIN_VIEW_NAME);
		modelAndView.addObject(ABOUT.getName(), ABOUT.getValue());

		return modelAndView;
	}

}
