package pl.mm.documentArchive.daoRepository.integrationTest;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;
import pl.mm.documentArchive.daoRepository.DocumentRepository;
import pl.mm.documentArchive.daoRepository.UserRepository;
import pl.mm.documentArchive.model.db.Document;
import pl.mm.documentArchive.model.db.User;

public class ITDocumentRepositoryTest extends BaseDaoRepositoryTest {

	static final String ADD_DOCUMENTS_GROUP = "ADD_DOCUMENTS_GROUP";
	static final String DELETE_DOCUMENTS_GROUP = "deleteDocumentsGroup";

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private DocumentRepository documentRepository;

	@Test(dependsOnGroups = {ITUserRepositoryTest.ADD_USERS_GROUP}, dataProvider = DOCUMENTS_TEST_DATA_PROVIDER_NAME,
			groups = {ADD_DOCUMENTS_GROUP})
	public void addTestDocument(Document document) {
		User foundUser = userRepository.findByUserName(document.getDocumentOwner().getUserName()).orElse(null);

		document.setDocumentOwner(foundUser);

		documentRepository.save(document);
	}

	@Test(dependsOnMethods = {"addTestDocument"},
			dependsOnGroups = {ITDocumentMetaDataRepositoryTest.ADD_DOCUMENT_META_DATA_GROUP},
			dataProvider = DOCUMENTS_TEST_DATA_PROVIDER_NAME, groups = {DELETE_DOCUMENTS_GROUP})
	public void deleteDocument(Document document) {
		Document documentToDelete = documentRepository.findByDocumentName(document.getDocumentName()).orElse(null);
		documentRepository.delete(documentToDelete);
	}

}
