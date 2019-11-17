package pl.mm.documentArchive.webApp.viewController.utilities;

import pl.mm.documentArchive.model.User;

public enum AttributeNameWithValueForView {
	USER_MODEL("user", new User()),
	REGISTRATION_FORM_ERROR("registrationFormError", "User user already exists"),
	MAIN_PAGE("page", "mainPage"),
	ABOUT_PAGE("page", "about"),
	REGISTRATION_FROM_PAGE("page", "user/registrationForm"),
	REGISTRATION_CONFIRMATION_PAGE("page", "user/registrationConformation");

	private String name;
	private Object value;

	AttributeNameWithValueForView(String name, Object value) {
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public Object getValue() {
		return value;
	}

}
