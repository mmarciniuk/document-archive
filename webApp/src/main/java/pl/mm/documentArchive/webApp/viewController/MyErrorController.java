package pl.mm.documentArchive.webApp.viewController;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@RequestMapping(MyErrorController.RequestMappings.BASE)
@Controller
public class MyErrorController implements ErrorController {

	static class RequestMappings {
		@SuppressWarnings("unused")
		static final String BASE = "";
		@SuppressWarnings("WeakerAccess")
		public static final String ERROR = "/error";
	}

	@GetMapping({RequestMappings.ERROR})
	public ModelAndView showError(HttpServletRequest httpServletRequest) {
		ModelAndView modelAndView = new ModelAndView("errorPages/error.html");

		String errorMsg;
		int httpErrorCode = this.getErrorCode(httpServletRequest);
		switch (httpErrorCode) {
			case 400:
				errorMsg = "Http Error Code: 400. Bad Request";
				break;
			case 401:
				errorMsg = "Http Error Code: 401. Unauthorized";
				break;
			case 404:
				errorMsg = "Http Error Code: 404. Resource not found";
				break;
			case 500:
				errorMsg = "Http Error Code: 500. Internal Server Error";
				break;
			default:
				errorMsg = "General error";
		}
		modelAndView.addObject("errorMsg", errorMsg);

		return modelAndView;
	}

	private int getErrorCode(HttpServletRequest httpRequest) {
		return (Integer) httpRequest.getAttribute("javax.servlet.error.status_code");
	}

	@Override
	public String getErrorPath() {
		return RequestMappings.ERROR;
	}

}
