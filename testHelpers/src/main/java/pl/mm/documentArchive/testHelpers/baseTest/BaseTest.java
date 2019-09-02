package pl.mm.documentArchive.testHelpers.baseTest;

import org.slf4j.Logger;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeMethod;

public abstract class BaseTest extends AbstractTestNGSpringContextTests {

	private final Logger logger;

	public BaseTest(Logger logger) {
		this.logger = logger;
	}

	@BeforeMethod
	public void beforeTestMethod(Object[] testData) {
		if(testData != null) {
			for (int i = 0; i < testData.length; i++) {
				if(testData[i] != null)
					logger.info("Test data for: [" + i + "] " + testData[i].toString());
			}
		}
	}

}
