package pl.mm.documentArchive.service.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.testng.Assert;
import org.testng.annotations.Test;
import pl.mm.documentArchive.daoRepository.RoleRepository;
import pl.mm.documentArchive.model.Role;
import pl.mm.documentArchive.model.User;
import pl.mm.documentArchive.service.BaseServiceTest;
import pl.mm.documentArchive.service.UserService;
import pl.mm.documentArchive.service.dataProvider.UserTestDataProvider;

import java.util.ArrayList;
import java.util.List;

public class ITUserServiceTest extends BaseServiceTest {

	static final String GROUP_CREATE_USER_AND_LOGIN = "GROUP_CREATE_USER_AND_LOGIN";

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private UserService userService;

	private void loadRolesFromDataBase(User user) {
		List<Role> loadedRolesFromDb = new ArrayList<>();
		user.getRoles().forEach(r -> loadedRolesFromDb.add(roleRepository.findByRoleName(r.getRoleName()).orElse(null)));
		user.setRoles(loadedRolesFromDb);
	}

	@Test(dataProviderClass = UserTestDataProvider.class, dataProvider = UserTestDataProvider.USERS_DATA_PROVIDER_NAME,
		groups = {GROUP_CREATE_USER_AND_LOGIN})
	public void createUser(User user) {
		loadRolesFromDataBase(user);
		userService.addUser(user);
	}

	@Test(dataProviderClass = UserTestDataProvider.class, dataProvider = UserTestDataProvider.USERS_DATA_PROVIDER_NAME,
		dependsOnMethods = {"createUser"}, groups = {GROUP_CREATE_USER_AND_LOGIN})
	public void simulateLogIn(User userToLogin) {
		UserDetails loadedUser = userService.loadUserByUsername(userToLogin.getUserName());
		boolean isPasswordMatch = passwordEncoder.matches(userToLogin.getPassword(), loadedUser.getPassword());

		Assert.assertTrue(isPasswordMatch);
	}

	@Test(dataProviderClass = UserTestDataProvider.class, dataProvider = UserTestDataProvider.USERS_DATA_PROVIDER_NAME,
			dependsOnMethods = {"simulateLogIn"})
	public void removeAccount(User accountToRemove) {
		userService.removeUser(accountToRemove);
	}

}
