package pl.mm.documentArchive.testHelpers.dataProviderLoader;

import org.apache.poi.xssf.usermodel.XSSFRow;
import pl.mm.documentArchive.model.db.Role;

public class DataProviderLoaderFromXlsRole extends DataProviderLoaderFromXls<Role> {

	public DataProviderLoaderFromXlsRole(String pathToXls, String sheetName) {
		super(pathToXls, sheetName);
	}

	@Override
	public Role mapInputData(Object obj) {
		XSSFRow row = (XSSFRow) obj;
		Role roleFormSheet = new Role();
		roleFormSheet.setRoleName(row.getCell(0).getStringCellValue());
		return roleFormSheet;
	}

}
