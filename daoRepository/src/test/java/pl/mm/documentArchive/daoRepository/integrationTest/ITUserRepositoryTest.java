package pl.mm.documentArchive.daoRepository.integrationTest;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;
import pl.mm.documentArchive.daoRepository.RoleRepository;
import pl.mm.documentArchive.daoRepository.UserRepository;
import pl.mm.documentArchive.model.Role;
import pl.mm.documentArchive.model.User;

import java.util.ArrayList;
import java.util.List;

public class ITUserRepositoryTest extends BaseDaoRepositoryTest {

	static final String ADD_USERS_GROUP = "ADD_USERS_GROUP";

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;

	private void loadRolesFromDataBase(User user) {
		List<Role> loadedRolesFromDb = new ArrayList<>();
		user.getRoles()
				.forEach(r -> loadedRolesFromDb.add(roleRepository.findByRoleName(r.getRoleName()).orElse(null)));
		user.setRoles(loadedRolesFromDb);
	}

	@Test(dependsOnGroups = {ITRoleRepositoryTest.CHECK_IF_ROLE_EXISTS_GROUP},
			dataProvider = USERS_DATA_PROVIDER_NAME, groups = {ADD_USERS_GROUP})
	public void addUser(User user) {
		loadRolesFromDataBase(user);
		userRepository.save(user);
	}

	@Test(dependsOnMethods = {"addUser"}, dataProvider = USERS_DATA_PROVIDER_NAME,
			dependsOnGroups = {ITDocumentRepositoryTest.DELETE_DOCUMENTS_GROUP})
	public void deleteUser(User user) {
		User userToDelete = userRepository.findByUserName(user.getUserName()).orElse(null);
		userRepository.delete(userToDelete);
	}

}
