package pl.mm.documentArchive.daoRepository.dataProvider;


import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.io.ClassPathResource;
import org.testng.annotations.DataProvider;
import pl.mm.documentArchive.model.Role;
import pl.mm.documentArchive.testHelpers.DataProviderHelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RoleTestDataProvider {

	public static final String ROLES_DATA_PROVIDER_NAME = "rolesDataProvider";

	@DataProvider(name = ROLES_DATA_PROVIDER_NAME, parallel = true)
	public static Object[][] rolesDataProvider() throws IOException {
		ClassPathResource classPathResource = new ClassPathResource("/rolesTestData/rolesList.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(classPathResource.getInputStream());
		XSSFSheet sheet = workbook.getSheet("rolesList");

		List<Role> roles = new ArrayList<>();
		for(int i=1; i<sheet.getPhysicalNumberOfRows(); i++) {
			XSSFRow row = sheet.getRow(i);

			Role roleFormSheet = new Role();
			roleFormSheet.setRoleName(row.getCell(0).getStringCellValue());

			roles.add(roleFormSheet);
		}
		return DataProviderHelper.convertToObject(roles);
	}

}
