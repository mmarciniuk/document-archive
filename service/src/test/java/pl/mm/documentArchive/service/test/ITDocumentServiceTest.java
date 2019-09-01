package pl.mm.documentArchive.service.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;
import pl.mm.documentArchive.service.BaseServiceTest;
import pl.mm.documentArchive.service.DocumentService;

public class ITDocumentServiceTest extends BaseServiceTest {

	@Autowired
	private DocumentService documentService;

	@Test(enabled = false)
	public void addDocument() {

	}

}
