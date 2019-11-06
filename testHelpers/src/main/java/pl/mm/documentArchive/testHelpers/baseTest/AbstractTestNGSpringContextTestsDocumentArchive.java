package pl.mm.documentArchive.testHelpers.baseTest;

import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import pl.mm.documentArchive.testHelpers.dataProviderLoader.DataProviderLoaderFromXlsDocument;
import pl.mm.documentArchive.testHelpers.dataProviderLoader.DataProviderLoaderFromXlsDocumentMetaData;
import pl.mm.documentArchive.testHelpers.dataProviderLoader.DataProviderLoaderFromXlsRole;
import pl.mm.documentArchive.testHelpers.dataProviderLoader.DataProviderLoaderFromXlsUser;

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

	protected static final String ROLES_DATA_PROVIDER_NAME = "rolesDataProvider";

	@DataProvider(name = ROLES_DATA_PROVIDER_NAME, parallel = true)
	public Object[][] rolesDataProvider() throws Exception {
		DataProviderLoaderFromXlsRole loaderFromXlsRole = new DataProviderLoaderFromXlsRole("/testData/testData.xlsx",
				"rolesList");
		return loaderFromXlsRole.loadTestData();
	}

	protected static final String USERS_DATA_PROVIDER_NAME = "usersDataProvider";

	@DataProvider(name = USERS_DATA_PROVIDER_NAME, parallel = true)
	public Object[][] usersDataProvider() throws Exception {
		DataProviderLoaderFromXlsUser loaderFromXlsUser = new DataProviderLoaderFromXlsUser("/testData/testData.xlsx",
				"usersList");
		return loaderFromXlsUser.loadTestData();
	}

	protected static final String DOCUMENTS_TEST_DATA_PROVIDER_NAME = "documentsTestDataProvider";

	@DataProvider(name = DOCUMENTS_TEST_DATA_PROVIDER_NAME, parallel = true)
	public Object[][] testDocuments() throws Exception {
		DataProviderLoaderFromXlsDocument loaderFromXlsDocument = new DataProviderLoaderFromXlsDocument(
				"/testData/testData.xlsx", "documentsList");
		return loaderFromXlsDocument.loadTestData();
	}

	protected static final String DOCUMENT_META_DATA_TEST_DATA_PROVIDER_NAME = "DOCUMENT_META_DATA_TEST_DATA_PROVIDER_NAME";

	@DataProvider(name = DOCUMENT_META_DATA_TEST_DATA_PROVIDER_NAME)
	public Object[][] metaDataForDocuments() throws Exception {
		DataProviderLoaderFromXlsDocumentMetaData loaderFromXlsDocumentMetaData =
				new DataProviderLoaderFromXlsDocumentMetaData("./testData/testData.xlsx", "documentMetaDataList");
		return loaderFromXlsDocumentMetaData.loadTestData();
	}

}
