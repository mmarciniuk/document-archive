package pl.mm.documentArchive.service.integrationTest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.testng.Assert;
import org.testng.annotations.Test;
import pl.mm.documentArchive.model.User;
import pl.mm.documentArchive.service.user.UserAlreadyExists;
import pl.mm.documentArchive.service.user.UserService;

public class ITUserServiceTest extends BaseServiceTest {

	static final String GROUP_CREATE_USER_AND_LOGIN = "GROUP_CREATE_USER_AND_LOGIN";

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	@Autowired
	private UserService userService;

	@Test(dataProvider = USERS_DATA_PROVIDER_NAME, groups = {GROUP_CREATE_USER_AND_LOGIN})
	public void createUser(User user) throws UserAlreadyExists {
		userService.addUser(user);
	}

	@Test(dataProvider = USERS_DATA_PROVIDER_NAME, dependsOnMethods = {"createUser"})
	public void findUserByUuid(User user) {
		User userFoundByUserName = userService.findByUserName(user.getUserName());

		User userFoundByUuid = userService.findByUuid(userFoundByUserName.getUuid(), User.class);

		Assert.assertNotNull(userFoundByUuid);
		Assert.assertEquals(user.getUserName(), userFoundByUuid.getUserName());
	}

	@Test(dataProvider = USERS_DATA_PROVIDER_NAME, dependsOnMethods = {"createUser"},
			groups = {GROUP_CREATE_USER_AND_LOGIN})
	public void simulateLogIn(User userToLogin) {
		UserDetails loadedUser = userService.loadUserByUsername(userToLogin.getUserName());
		boolean isPasswordMatch = passwordEncoder.matches(userToLogin.getPassword(), loadedUser.getPassword());

		Assert.assertTrue(isPasswordMatch);
	}

	@Test(dataProvider = USERS_DATA_PROVIDER_NAME, dependsOnMethods = {"simulateLogIn"})
	public void deleteUser(User accountToRemove) {
		userService.deleteUser(accountToRemove);
	}

}
