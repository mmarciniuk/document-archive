package pl.mm.documentArchive.webApp.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.mm.documentArchive.model.Role;
import pl.mm.documentArchive.service.RoleService;

import java.util.List;

@RestController
@RequestMapping(BaseRest.RequestMappings.REST_API_BASE+RoleRestController.RequestMappings.BASE)
public class RoleRestController extends BaseRest {

	@Autowired
	private RoleService roleService;

	@SuppressWarnings("WeakerAccess")
	public static class RequestMappings {
		public static final String BASE = "/role";
		public static final String LIST_ROLES = "/listRoles";
	}

	@GetMapping(path = RequestMappings.LIST_ROLES)
	public List<Role> listRoles() {
		return roleService.roleList();
	}

}
