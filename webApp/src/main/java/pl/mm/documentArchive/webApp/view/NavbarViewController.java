package pl.mm.documentArchive.webApp.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(BaseViewController.RequestMappings.BASE)
public class NavbarViewController extends BaseViewController {

	@GetMapping(value = {RequestMappings.BASE, RequestMappings.INDEX, RequestMappings.MAIN_PAGE})
	public ModelAndView index() {
		ModelAndView modelAndView = new ModelAndView(MAIN_VIEW_NAME);
		modelAndView.addObject(AttributeNameForModels.MAIN_PAGE.getName(), AttributeNameForModels.MAIN_PAGE.getValue());

		return modelAndView;
	}

	@GetMapping(value = {RequestMappings.ABOUT})
	public ModelAndView about() {
		ModelAndView modelAndView = new ModelAndView(MAIN_VIEW_NAME);
		modelAndView.addObject(AttributeNameForModels.ABOUT.getName(), AttributeNameForModels.ABOUT.getValue());

		return modelAndView;
	}

	@GetMapping(value = {RequestMappings.REGISTRATION_FROM})
	public ModelAndView registrationForm() {
		ModelAndView modelAndView = new ModelAndView(MAIN_VIEW_NAME);
		modelAndView.addObject(AttributeNameForModels.REGISTRATION_FROM.getName(),
				AttributeNameForModels.REGISTRATION_FROM.getValue());

		return modelAndView;
	}

}
