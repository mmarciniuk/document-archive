package pl.mm.documentArchive.webApp.viewController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import pl.mm.documentArchive.model.db.User;
import pl.mm.documentArchive.service.user.UserAlreadyExists;
import pl.mm.documentArchive.service.user.UserService;
import pl.mm.documentArchive.webApp.viewController.utilities.AttributeNameWithValueForView;

import javax.validation.Valid;

@RequestMapping(RegistrationController.RequestMappings.BASE)
@Controller
public class RegistrationController extends BaseViewController {

	static class RequestMappings {
		static final String BASE = "/register";
		static final String REGISTRATION_FROM = "/createAccount";
		static final String PROCESS_REGISTRATION = "/processRegistration";
	}

	@Autowired
	private UserService userService;

	@GetMapping(RequestMappings.REGISTRATION_FROM)
	public ModelAndView registrationForm() {
		ModelAndView modelAndView = new ModelAndView(MAIN_VIEW_NAME);
		modelAndView.addObject(AttributeNameWithValueForView.REGISTRATION_FROM_PAGE.getName(),
				AttributeNameWithValueForView.REGISTRATION_FROM_PAGE.getValue());

		modelAndView.addObject(AttributeNameWithValueForView.USER_MODEL.getName(),
				AttributeNameWithValueForView.USER_MODEL.getValue());

		return modelAndView;
	}

	@PostMapping(RequestMappings.PROCESS_REGISTRATION)
	public String processRegistration(@Valid @ModelAttribute("user") User user, BindingResult bindingResult,
	                                  Model model) throws UserAlreadyExists {
		model.addAttribute(AttributeNameWithValueForView.REGISTRATION_FROM_PAGE.getName(),
				AttributeNameWithValueForView.REGISTRATION_FROM_PAGE.getValue());

		if (bindingResult.hasErrors()) {
			return MAIN_VIEW_NAME;
		}

		if (userService.checkIfUserExists(user)) {
			model.addAttribute(AttributeNameWithValueForView.USER_MODEL.getName(),
					AttributeNameWithValueForView.USER_MODEL.getValue());

			model.addAttribute(AttributeNameWithValueForView.REGISTRATION_FORM_ERROR.getName(),
					"User name already exists.");

			return MAIN_VIEW_NAME;
		} else {
			userService.addUser(user);

			model.addAttribute(AttributeNameWithValueForView.REGISTRATION_CONFIRMATION_PAGE.getName(),
					AttributeNameWithValueForView.REGISTRATION_CONFIRMATION_PAGE.getValue());

			return MAIN_VIEW_NAME;
		}
	}

}
