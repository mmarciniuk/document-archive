package pl.mm.documentArchive.service.dataProvider;

import org.testng.annotations.DataProvider;
import pl.mm.documentArchive.testHelpers.dataProviderLoader.DataProviderLoaderFromXlsDocument;

public class DocumentTestDataProvider {

	public static final String DOCUMENT_DATA_PROVIDER_NAME = "documentDataProvider";

	@DataProvider(name = DOCUMENT_DATA_PROVIDER_NAME, parallel = true)
	public static Object[][] usersDataProvider() throws Exception {
		DataProviderLoaderFromXlsDocument loaderFromXlsDocument = new DataProviderLoaderFromXlsDocument("/testData/testData.xlsx", "documentsList");
		return loaderFromXlsDocument.loadTestData();
	}

}
