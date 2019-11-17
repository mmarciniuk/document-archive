package pl.mm.documentArchive.testHelpers.dataProviderLoader;

import org.apache.poi.xssf.usermodel.XSSFRow;
import pl.mm.documentArchive.model.db.Document;
import pl.mm.documentArchive.model.db.DocumentMetaData;
import pl.mm.documentArchive.model.db.User;

import java.sql.Timestamp;
import java.util.UUID;

public class DataProviderLoaderFromXlsDocumentMetaData extends DataProviderLoaderFromXls<DocumentMetaData> {

	public DataProviderLoaderFromXlsDocumentMetaData(String classPathToXls, String sheetName) {
		super(classPathToXls, sheetName);
	}

	@Override
	public DocumentMetaData mapInputData(Object obj) {
		XSSFRow row = (XSSFRow) obj;

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

		return documentMetaDataFromSheet;
	}

}
