package pl.mm.documentArchive.daoRepository.integrationTest;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;
import pl.mm.documentArchive.daoRepository.DocumentMetaDataRepository;
import pl.mm.documentArchive.daoRepository.DocumentRepository;
import pl.mm.documentArchive.daoRepository.dataProvider.DocumentMetaDataTestDataProvider;
import pl.mm.documentArchive.model.Document;
import pl.mm.documentArchive.model.DocumentMetaData;

public class ITDocumentMetaDataRepositoryTest extends BaseDaoRepositoryTest {

	static final String ADD_DOCUMENT_META_DATA_GROUP = "ADD_DOCUMENT_META_DATA_GROUP";
	@SuppressWarnings("WeakerAccess")
	static final String DELETE_DOCUMENT_META_DATA_GROUP = "REMOVE_DOCUMENT_META_DATA_GROUP";

	@Autowired
	private DocumentRepository documentRepository;
	@Autowired
	private DocumentMetaDataRepository documentMetaDataRepository;


	@Test(dataProviderClass = DocumentMetaDataTestDataProvider.class,
			dataProvider = DocumentMetaDataTestDataProvider.DOCUMENT_META_DATA_TEST_DATA_PROVIDER_NAME,
			dependsOnGroups = {ITDocumentRepositoryTest.ADD_DOCUMENTS_GROUP}, groups = {ADD_DOCUMENT_META_DATA_GROUP})
	public void addDocumentMetaData(DocumentMetaData documentMetaData) {
		Document documentToFind = documentMetaData.getDocument();
		Document foundDocument = documentRepository.findByDocumentNameAndUserName(documentToFind.getDocumentName(),
				documentToFind.getDocumentOwner().getUserName()).orElse(null);

		documentMetaData.setDocument(foundDocument);

		documentMetaDataRepository.save(documentMetaData);
	}

	@Test(dataProviderClass = DocumentMetaDataTestDataProvider.class,
			dataProvider = DocumentMetaDataTestDataProvider.DOCUMENT_META_DATA_TEST_DATA_PROVIDER_NAME,
			dependsOnMethods = {"addDocumentMetaData"}, groups = {DELETE_DOCUMENT_META_DATA_GROUP})
	public void deleteDocumentMetaData(DocumentMetaData documentMetaData) {
		DocumentMetaData documentMetaDataToDelete = documentMetaDataRepository.findByNameAndDocumentAndUserName(
				documentMetaData.getName(), documentMetaData.getDocument().getDocumentName(),
				documentMetaData.getDocument().getDocumentOwner().getUserName()).orElse(null);

		documentMetaDataRepository.delete(documentMetaDataToDelete);
	}


}
