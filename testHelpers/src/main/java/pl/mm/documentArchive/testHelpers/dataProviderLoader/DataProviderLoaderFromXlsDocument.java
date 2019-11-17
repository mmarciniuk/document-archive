package pl.mm.documentArchive.testHelpers.dataProviderLoader;

import org.apache.commons.compress.utils.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.springframework.core.io.ClassPathResource;
import pl.mm.documentArchive.model.db.Document;
import pl.mm.documentArchive.model.db.User;

import java.sql.Timestamp;
import java.util.Objects;
import java.util.UUID;

public class DataProviderLoaderFromXlsDocument extends DataProviderLoaderFromXls<Document> {

	public DataProviderLoaderFromXlsDocument(String classPathToXls, String sheetName) {
		super(classPathToXls, sheetName);
	}

	@Override
	public Document mapInputData(Object obj) throws Exception {
		XSSFRow row = (XSSFRow) obj;

		User userFromSheet = new User();
		userFromSheet.setUserName(row.getCell(0).getStringCellValue());

		ClassPathResource classPathResourceDocument = new ClassPathResource(row.getCell(1).getStringCellValue());
		Document documentFromSheet = new Document();
		documentFromSheet.setUuid(UUID.randomUUID().toString());
		documentFromSheet.setCreatedDateTime(new Timestamp(System.currentTimeMillis()));
		documentFromSheet.setDocumentName(classPathResourceDocument.getFilename());
		String[] split = Objects.requireNonNull(classPathResourceDocument.getFilename()).split("\\.");
		documentFromSheet.setExtension(split[1]);


		documentFromSheet.setDocumentBlob(IOUtils.toByteArray(classPathResourceDocument.getInputStream()));
		documentFromSheet.setDocumentOwner(userFromSheet);
		return documentFromSheet;
	}

}
