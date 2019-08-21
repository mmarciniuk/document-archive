package pl.mm.documentArchive.daoRepository.dataProvider;


import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.io.ClassPathResource;
import org.testng.annotations.DataProvider;
import pl.mm.documentArchive.model.Document;
import pl.mm.documentArchive.model.DocumentMetaData;
import pl.mm.documentArchive.model.User;
import pl.mm.documentArchive.testHelpers.DataProviderHelper;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DocumentMetaDataTestDataProvider {

	public static final String DOCUMENT_META_DATA_TEST_DATA_PROVIDER_NAME = "DOCUMENT_META_DATA_TEST_DATA_PROVIDER_NAME";

	@DataProvider(name = DOCUMENT_META_DATA_TEST_DATA_PROVIDER_NAME)
	public Object[][] testDocuments() throws IOException {
		ClassPathResource classPathResource = new ClassPathResource("./documentMetaDataList/documentMetaDataList.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(classPathResource.getInputStream());
		XSSFSheet sheet = workbook.getSheet("documentMetaDataList");

		List<DocumentMetaData> documentMetaDataList = new ArrayList<>();
		for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
			XSSFRow row = sheet.getRow(i);

			User ownerOfDocumentFromSheet = new User();
			ownerOfDocumentFromSheet.setUserName(row.getCell(0).getStringCellValue());

			Document documentNameFromSheet = new Document();
			documentNameFromSheet.setDocumentName(row.getCell(1).getStringCellValue());
			documentNameFromSheet.setDocumentOwner(ownerOfDocumentFromSheet);

			DocumentMetaData documentMetaDataFromSheet = new DocumentMetaData();
			documentMetaDataFromSheet.setUuid(UUID.randomUUID().toString());
			documentMetaDataFromSheet.setCreatedDateTime(new Timestamp(System.currentTimeMillis()));
			documentMetaDataFromSheet.setName(row.getCell(2).getStringCellValue());
			documentMetaDataFromSheet.setValue(row.getCell(3).getStringCellValue());
			documentMetaDataFromSheet.setDocument(documentNameFromSheet);

			documentMetaDataList.add(documentMetaDataFromSheet);
		}
		return DataProviderHelper.convertToObject(documentMetaDataList);
	}

}
