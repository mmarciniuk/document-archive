package pl.mm.documentArchive.testHelpers.dataProviderLoader;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import pl.mm.documentArchive.model.db.Role;
import pl.mm.documentArchive.model.db.User;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DataProviderLoaderFromXlsUser extends DataProviderLoaderFromXls<User> {

	public DataProviderLoaderFromXlsUser(String classPathToXls, String sheetName) {
		super(classPathToXls, sheetName);
	}

	@Override
	public User mapInputData(Object obj) {
		XSSFRow row = (XSSFRow) obj;
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

		return userFromSheet;
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
