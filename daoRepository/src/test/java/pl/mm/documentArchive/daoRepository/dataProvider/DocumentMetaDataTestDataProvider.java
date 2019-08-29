package pl.mm.documentArchive.daoRepository.dataProvider;


import org.testng.annotations.DataProvider;
import pl.mm.documentArchive.testHelpers.dataProviderLoader.DataProviderLoaderFromXlsDocumentMetaData;

public class DocumentMetaDataTestDataProvider {

	public static final String DOCUMENT_META_DATA_TEST_DATA_PROVIDER_NAME = "DOCUMENT_META_DATA_TEST_DATA_PROVIDER_NAME";

	@DataProvider(name = DOCUMENT_META_DATA_TEST_DATA_PROVIDER_NAME)
	public Object[][] testDocuments() throws Exception {
		DataProviderLoaderFromXlsDocumentMetaData loaderFromXlsDocumentMetaData =
				new DataProviderLoaderFromXlsDocumentMetaData("./testData/testData.xlsx", "documentMetaDataList");
		return loaderFromXlsDocumentMetaData.loadTestData();
	}

}
