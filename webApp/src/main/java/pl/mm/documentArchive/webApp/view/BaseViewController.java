package pl.mm.documentArchive.webApp.view;

abstract class BaseViewController {

	static final String MAIN_VIEW_NAME = "index";

	static class RequestMappings {
		static final String BASE = "";
		static final String INDEX = BASE + "/index";
		static final String MAIN_PAGE = BASE + "/mainPage";
		static final String ABOUT = BASE + "/about";
	}

	public enum AttributeNameForModels {
		MAIN_PAGE("page", "mainPage"),
		ABOUT("page", "about");

		private String name;
		private Object value;

		AttributeNameForModels(String name, Object value) {
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

}
