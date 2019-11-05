package pl.mm.documentArchive.daoRepository.integrationTest;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.Test;
import pl.mm.documentArchive.daoRepository.RoleRepository;
import pl.mm.documentArchive.model.Role;

import java.util.ArrayList;
import java.util.List;

public class ITRoleRepositoryTest extends BaseDaoRepositoryTest {

	static final String CHECK_IF_ROLE_EXISTS_GROUP = "checkIfRoleExists";

	@Autowired
	private RoleRepository roleRepository;

	private final List<Role> roles = new ArrayList<>();

	@Test(groups = {CHECK_IF_ROLE_EXISTS_GROUP}, dataProvider = ROLES_DATA_PROVIDER_NAME)
	public void checkIfRoleExists(Role role) {
		Role foundRole = roleRepository.findByRoleName(role.getRoleName()).orElse(null);
		Assert.assertNotNull(foundRole);
		roles.add(foundRole);
	}

	@Test(dependsOnMethods = {"checkIfRoleExists"}, groups = {CHECK_IF_ROLE_EXISTS_GROUP})
	public void findRolesByUuid() {
		for (Role expectedRole : roles) {
			Role actualRole = roleRepository.findByUuid(expectedRole.getUuid(), Role.class).orElse(null);
			Assert.assertEquals(actualRole, expectedRole);
		}
	}

}
