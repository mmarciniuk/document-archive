package pl.mm.documentArchive.webApp.viewController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import pl.mm.documentArchive.webApp.viewController.utilities.attribiutesForView.AttributeNameWithValueForView;

@RequestMapping(IndexController.RequestMappings.BASE)
@Controller
public class IndexController extends BaseViewController {

	public static class RequestMappings {
		public static final String BASE = "";
		static final String INDEX = "/index";
		static final String MAIN_PAGE = "/mainPage";
		static final String ABOUT = "/about";
	}

	@GetMapping(value = {RequestMappings.BASE, RequestMappings.INDEX, RequestMappings.MAIN_PAGE})
	public ModelAndView index() {
		ModelAndView modelAndView = new ModelAndView(MAIN_VIEW_NAME);
		modelAndView.addObject(AttributeNameWithValueForView.MAIN_PAGE.getName(),
				AttributeNameWithValueForView.MAIN_PAGE.getValue());

		return modelAndView;
	}

	@GetMapping(value = {RequestMappings.ABOUT})
	public ModelAndView about() {
		ModelAndView modelAndView = new ModelAndView(MAIN_VIEW_NAME);
		modelAndView.addObject(AttributeNameWithValueForView.ABOUT_PAGE.getName(),
				AttributeNameWithValueForView.ABOUT_PAGE.getValue());

		return modelAndView;
	}

}
