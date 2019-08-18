package pl.mm.documentArchive.daoRepository.tests;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.Test;
import pl.mm.documentArchive.daoRepository.BaseTest;
import pl.mm.documentArchive.daoRepository.RoleRepository;
import pl.mm.documentArchive.model.Role;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ITRoleRepositoryTest extends BaseTest {

	static final String GROUP_NAME = "ITRoleRepositoryTestGroup";
	static final String ADMIN_ROLE_NAME = "ADMIN";
	private static final String USER_ROLE_NAME = "USER";

	@Autowired
	private RoleRepository roleRepository;

	private List<Role> roles = new ArrayList<>();

	@Test(priority = 1, groups = {GROUP_NAME})
	public void checkIfAdminRoleExists() {
		Role actualAdminRoleResult = roleRepository.findByName(ADMIN_ROLE_NAME).orElse(null);
		Assert.assertEquals(Objects.requireNonNull(actualAdminRoleResult).getRoleName(), ADMIN_ROLE_NAME);
		roles.add(actualAdminRoleResult);
	}

	@Test(priority = 2, groups = {GROUP_NAME})
	public void checkIfUserRoleExists() {
		Role actualUserRole = roleRepository.findByName(USER_ROLE_NAME).orElse(null);
		Assert.assertEquals(Objects.requireNonNull(actualUserRole).getRoleName(), USER_ROLE_NAME);
		roles.add(actualUserRole);
	}

	@Test(dependsOnMethods = {"checkIfAdminRoleExists", "checkIfUserRoleExists"}, groups = {GROUP_NAME})
	public void findRolesByUuid() {
		for (Role expectedRole : roles) {
			Role actualRole = roleRepository.findByUuid(expectedRole.getUuid(), Role.class).orElse(null);
			Assert.assertEquals(actualRole, expectedRole);
		}
	}

}
