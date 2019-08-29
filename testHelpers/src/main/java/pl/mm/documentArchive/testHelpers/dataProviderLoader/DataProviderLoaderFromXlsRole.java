package pl.mm.documentArchive.testHelpers.dataProviderLoader;

import org.apache.poi.xssf.usermodel.XSSFRow;
import pl.mm.documentArchive.model.Role;

public class DataProviderLoaderFromXlsRole extends DataProviderLoaderFromXls<Role> {

	public DataProviderLoaderFromXlsRole(ResourcesType resourcesType, String pathToXls, String sheetName) {
		super(resourcesType, pathToXls, sheetName);
	}

	@Override
	public Role mapInputData(Object obj) throws Exception {
		XSSFRow row = (XSSFRow) obj;
		Role roleFormSheet = new Role();
		roleFormSheet.setRoleName(row.getCell(0).getStringCellValue());
		return roleFormSheet;
	}

}
