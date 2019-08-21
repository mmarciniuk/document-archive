package pl.mm.documentArchive.daoRepository.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;
import pl.mm.documentArchive.daoRepository.BaseTest;
import pl.mm.documentArchive.daoRepository.DocumentRepository;
import pl.mm.documentArchive.daoRepository.UserRepository;
import pl.mm.documentArchive.daoRepository.dataProvider.DocumentTestDataProvider;
import pl.mm.documentArchive.model.Document;
import pl.mm.documentArchive.model.User;

public class ITDocumentRepositoryUnitTest extends BaseTest {

	static final String ADD_DOCUMENTS_GROUP = "ADD_DOCUMENTS_GROUP";
	static final String DELETE_DOCUMENTS_GROUP = "deleteDocumentsGroup";

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private DocumentRepository documentRepository;

	@Test(dependsOnGroups = {ITUserRepositoryUnitTest.ADD_USERS_GROUP}, dataProviderClass = DocumentTestDataProvider.class,
			dataProvider = DocumentTestDataProvider.DOCUMENTS_TEST_DATA_PROVIDER_NAME, groups = {ADD_DOCUMENTS_GROUP})
	public void addTestDocuments(Document document) {
		User foundUser = userRepository.findByUserName(document.getDocumentOwner().getUserName()).orElse(null);

		document.setDocumentOwner(foundUser);

		documentRepository.save(document);
	}

	@Test(dependsOnMethods = {"addTestDocuments"}, dependsOnGroups = {ITDocumentMetaDataRepositoryUnitTest.ADD_DOCUMENT_META_DATA_GROUP},
			dataProviderClass = DocumentTestDataProvider.class,
			dataProvider = DocumentTestDataProvider.DOCUMENTS_TEST_DATA_PROVIDER_NAME, groups = {DELETE_DOCUMENTS_GROUP})
	public void deleteDocuments(Document document) {
		Document documentToDelete = documentRepository.findByDocumentName(document.getDocumentName()).orElse(null);
		documentRepository.delete(documentToDelete);
	}

}
