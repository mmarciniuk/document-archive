package pl.mm.documentArchive.daoRepository.tests;

import org.apache.commons.compress.utils.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pl.mm.documentArchive.daoRepository.BaseTest;
import pl.mm.documentArchive.daoRepository.DocumentRepository;
import pl.mm.documentArchive.daoRepository.UserRepository;
import pl.mm.documentArchive.model.Document;
import pl.mm.documentArchive.model.User;
import pl.mm.documentArchive.testHelpers.DataProviderHelper;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class ITDocumentRepositoryUnitTest extends BaseTest {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private DocumentRepository documentRepository;

	@DataProvider
	public Object[][] testDocuments() throws IOException {
		ClassPathResource classPathResource = new ClassPathResource("/documentsList/documentsList.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(classPathResource.getInputStream());
		XSSFSheet sheet = workbook.getSheet("documentsList");

		List<Document> documents = new ArrayList<>();
		for(int i=1; i < sheet.getPhysicalNumberOfRows(); i++) {
			XSSFRow row = sheet.getRow(i);

			User userFromSheet = new User();
			userFromSheet.setUserName(row.getCell(0).getStringCellValue());

			ClassPathResource classPathResourceDocument = new ClassPathResource(row.getCell(1).getStringCellValue());
			Document documentFromSheet = new Document();
			documentFromSheet.setUuid(UUID.randomUUID().toString());
			documentFromSheet.setCreatedDateTime(new Timestamp(System.currentTimeMillis()));
			documentFromSheet.setDocumentName(classPathResourceDocument.getFilename());
			String[] split = Objects.requireNonNull(classPathResource.getFilename()).split("\\.");
			documentFromSheet.setExtension(split[1]);
			documentFromSheet.setDocumentBlob(IOUtils.toByteArray(classPathResource.getInputStream()));
			documentFromSheet.setDocumentOwner(userFromSheet);

			documents.add(documentFromSheet);
		}

		return DataProviderHelper.convertToObject(documents);
	}

	@Test(dataProvider = "testDocuments", dependsOnGroups = {ITUserRepositoryUnitTest.ADD_USERS_GROUP})
	public void addTestDocuments(Document document) {
		User foundUser = userRepository.findByUserName(document.getDocumentOwner().getUserName()).orElse(null);

		document.setDocumentOwner(foundUser);

		documentRepository.save(document);
	}

}
