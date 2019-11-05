package pl.mm.documentArchive.testHelpers.baseTest;

import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeMethod;

public abstract class AbstractTestNGSpringContextTestsDocumentArchive extends AbstractTestNGSpringContextTests {

	@BeforeMethod
	public void beforeTestMethod(Object[] testData) {
		if (testData != null) {
			for (int i = 0; i < testData.length; i++) {
				if (testData[i] != null)
					logger.info("Test data for: [" + i + "] " + testData[i].toString());
			}
		}
	}

}
