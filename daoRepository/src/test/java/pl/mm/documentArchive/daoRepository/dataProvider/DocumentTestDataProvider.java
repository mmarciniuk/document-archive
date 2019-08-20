package pl.mm.documentArchive.daoRepository.dataProvider;

import org.apache.commons.compress.utils.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.io.ClassPathResource;
import org.testng.annotations.DataProvider;
import pl.mm.documentArchive.model.Document;
import pl.mm.documentArchive.model.User;
import pl.mm.documentArchive.testHelpers.DataProviderHelper;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class DocumentTestDataProvider {

	public static final String DOCUMENTS_TEST_DATA_PROVIDER_NAME = "documentsTestDataProvider";

	@DataProvider(name = DOCUMENTS_TEST_DATA_PROVIDER_NAME)
	public Object[][] testDocuments() throws IOException {
		ClassPathResource classPathResource = new ClassPathResource("/documentsList/documentsList.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(classPathResource.getInputStream());
		XSSFSheet sheet = workbook.getSheet("documentsList");

		List<Document> documents = new ArrayList<>();
		for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
			XSSFRow row = sheet.getRow(i);

			User userFromSheet = new User();
			userFromSheet.setUserName(row.getCell(0).getStringCellValue());

			ClassPathResource classPathResourceDocument = new ClassPathResource(row.getCell(1).getStringCellValue());
			Document documentFromSheet = new Document();
			documentFromSheet.setUuid(UUID.randomUUID().toString());
			documentFromSheet.setCreatedDateTime(new Timestamp(System.currentTimeMillis()));
			documentFromSheet.setDocumentName(classPathResourceDocument.getFilename());
			String[] split = Objects.requireNonNull(classPathResourceDocument.getFilename()).split("\\.");
			documentFromSheet.setExtension(split[1]);
			documentFromSheet.setDocumentBlob(IOUtils.toByteArray(classPathResource.getInputStream()));
			documentFromSheet.setDocumentOwner(userFromSheet);

			documents.add(documentFromSheet);
		}
		return DataProviderHelper.convertToObject(documents);
	}

}
