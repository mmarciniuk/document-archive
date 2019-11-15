package pl.mm.documentArchive.webApp.viewController.utilities;

import pl.mm.documentArchive.model.User;

public enum AttributeNameWithValueForView {
	USER_MODEL("user", new User()),
	MAIN_PAGE("page", "mainPage"),
	ABOUT_PAGE("page", "about"),
	REGISTRATION_FROM_PAGE("page", "registrationForm"),
	REGISTRATION_CONFIRMATION_PAGE("page", "registrationConfirmation");

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
