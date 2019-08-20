package pl.mm.documentArchive.daoRepository.dataProvider;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.io.ClassPathResource;
import org.testng.annotations.DataProvider;
import pl.mm.documentArchive.model.Role;
import pl.mm.documentArchive.model.User;
import pl.mm.documentArchive.testHelpers.DataProviderHelper;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserTestDataProvider {

	public static final String USERS_DATA_PROVIDER_NAME = "usersDataProvider";

	@DataProvider(name = USERS_DATA_PROVIDER_NAME, parallel = true)
	public static Object[][] usersDataProvider() throws IOException {
		ClassPathResource classPathResource = new ClassPathResource("/usersList/usersList.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(classPathResource.getInputStream());
		XSSFSheet sheet = workbook.getSheet("usersList");

		List<User> users = new ArrayList<>();
		for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
			XSSFRow row = sheet.getRow(i);
			List<Role> listOfRolesForUser = getUserRolesFromWorkbook(row.getCell(0));

			User userFromSheet = new User();
			userFromSheet.setUuid(UUID.randomUUID().toString());
			userFromSheet.setCreatedDateTime(new Timestamp(System.currentTimeMillis()));
			userFromSheet.setUserName(row.getCell(1).getStringCellValue());
			userFromSheet.setPassword(row.getCell(2).getStringCellValue());
			userFromSheet.setFirstName(row.getCell(3).getStringCellValue());
			userFromSheet.setLastName(row.getCell(4).getStringCellValue());
			userFromSheet.setAddressEmail(row.getCell(5).getStringCellValue());
			userFromSheet.setRoles(listOfRolesForUser);

			users.add(userFromSheet);
		}

		return DataProviderHelper.convertToObject(users);
	}

	private static List<Role> getUserRolesFromWorkbook(XSSFCell cellWithRoles) {
		String[] rolesOfUser = cellWithRoles.getStringCellValue().split("/|");
		List<Role> roles = new ArrayList<>();
		for (String roleName : rolesOfUser) {
			Role role = new Role();
			role.setRoleName(roleName);
			roles.add(role);
		}
		return roles;
	}

}
