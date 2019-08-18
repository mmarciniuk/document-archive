package pl.mm.documentArchive.daoRepository.tests;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pl.mm.documentArchive.daoRepository.BaseTest;
import pl.mm.documentArchive.daoRepository.RoleRepository;
import pl.mm.documentArchive.daoRepository.UserRepository;
import pl.mm.documentArchive.model.Role;
import pl.mm.documentArchive.model.User;
import pl.mm.documentArchive.testHelpers.DataProviderHelper;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.*;

public class ITUserRepositoryTest extends BaseTest {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;

	private User adminUser;

	private User createAdminUser() {
		Role adminRole = roleRepository.findByName(ITRoleRepositoryTest.ADMIN_ROLE_NAME).orElse(null);
		List<Role> roleList = new ArrayList<>();
		roleList.add(adminRole);

		User userToAdd = new User();
		userToAdd.setUuid(UUID.randomUUID().toString());
		userToAdd.setCreatedDateTime(new Timestamp(System.currentTimeMillis()));
		userToAdd.setUserName("autoTestUserName");
		userToAdd.setPassword("password");
		userToAdd.setFirstName("TestFirstName");
		userToAdd.setLastName("TestLastName");
		userToAdd.setAddressEmail("testEmailAddress@gmail.com");
		userToAdd.setRoles(roleList);

		return userToAdd;
	}

	@BeforeClass
	public void addAdminUser() {
		adminUser = userRepository.save(createAdminUser());
	}

	@AfterClass
	public void removeAdminUser() {
		userRepository.delete(adminUser);
	}

	@Test(expectedExceptions = {org.springframework.dao.DataIntegrityViolationException.class})
	public void tryToInsertAdminUserAgain() {
		userRepository.save(createAdminUser());
	}

	@Test(priority = 1)
	public void findUserById() {
		User foundUser = userRepository.findById(adminUser.getId()).orElse(null);
		Assert.assertEquals(Objects.requireNonNull(foundUser).getUserName(), adminUser.getUserName());
	}

	@Test(priority = 2)
	public void findUserByUuid() {
		User foundUser = userRepository.findByUuid(adminUser.getUuid(), User.class).orElse(null);
		Assert.assertEquals(Objects.requireNonNull(foundUser).getUserName(), adminUser.getUserName());
	}

	@Test(priority = 3, dataProvider = "usersTestDataProvider")
	public void addUser(User user) {
		userRepository.save(user);
	}

	@Test(priority = 4, dataProvider = "usersTestDataProvider")
	public void deleteUser(User user) {
		User userToDelete = userRepository.findByUserName(user.getUserName()).orElse(null);
		userRepository.delete(userToDelete);
	}

	@DataProvider
	public Object[][] usersTestDataProvider() throws IOException {
		ClassPathResource classPathResource = new ClassPathResource("/usersList/usersList.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(classPathResource.getInputStream());
		XSSFSheet sheet = workbook.getSheet("usersList");

		List<User> users = new ArrayList<>();
		for(int i=1; i<sheet.getPhysicalNumberOfRows(); i++) {
			XSSFRow row = sheet.getRow(i);
			List<Role> listOfRolesForUser = getUserRolesFromWorkBook(row.getCell(0));

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

	private List<Role> getUserRolesFromWorkBook(XSSFCell cellWithRoles) {
		List<String> rolesOfUser = Arrays.asList(cellWithRoles.getStringCellValue().split("/|"));
		return (List<Role>) roleRepository.findByName(rolesOfUser);
	}

}
