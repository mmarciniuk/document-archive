package pl.mm.documentArchive.daoRepository.dataProvider;

import org.testng.annotations.DataProvider;
import pl.mm.documentArchive.testHelpers.dataProviderLoader.DataProviderLoaderFromXlsDocument;

public class DocumentTestDataProvider {

	public static final String DOCUMENTS_TEST_DATA_PROVIDER_NAME = "documentsTestDataProvider";

	@DataProvider(name = DOCUMENTS_TEST_DATA_PROVIDER_NAME)
	public Object[][] testDocuments() throws Exception {
		DataProviderLoaderFromXlsDocument loaderFromXlsDocument = new DataProviderLoaderFromXlsDocument("/testData/testData.xlsx", "documentsList");
		return loaderFromXlsDocument.loadTestData();
	}

}
