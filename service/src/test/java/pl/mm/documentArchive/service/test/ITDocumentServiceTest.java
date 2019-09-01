package pl.mm.documentArchive.service.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.testng.annotations.Test;
import pl.mm.documentArchive.model.Document;
import pl.mm.documentArchive.service.BaseServiceTest;
import pl.mm.documentArchive.service.document.DocumentService;
import pl.mm.documentArchive.service.dataProvider.DocumentTestDataProvider;
import pl.mm.documentArchive.service.document.IncorrectOwnerOfDocument;

public class ITDocumentServiceTest extends BaseServiceTest {

	@Autowired
	private DocumentService documentService;

	public ITDocumentServiceTest() {
	}

	@Test(dataProviderClass = DocumentTestDataProvider.class, dataProvider = DocumentTestDataProvider.DOCUMENT_DATA_PROVIDER_NAME,
		dependsOnGroups = {ITUserServiceTest.GROUP_CREATE_USER_AND_LOGIN})
	public void addDocument(Document document) throws IncorrectOwnerOfDocument {
		documentService.addDocument(document);
	}

	@Test(dataProviderClass = DocumentTestDataProvider.class, dataProvider = DocumentTestDataProvider.DOCUMENT_DATA_PROVIDER_NAME,
		dependsOnMethods = {"addDocument"}, expectedExceptions = {InvalidDataAccessApiUsageException.class},
		description = "This test should throw error as document(s) should be deleted by deleteUser operation in ITUserServiceTest.")
	public void deleteDocument(Document document) {
		documentService.deleteDocument(document);
	}

}
