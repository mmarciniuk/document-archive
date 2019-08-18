package pl.mm.documentArchive.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.mm.documentArchive.daoRepository.RoleRepository;
import pl.mm.documentArchive.model.Role;

import java.util.List;

@Service
public class RoleService {

	@Autowired
	private RoleRepository roleRepository;

	public List<Role> roleList() {
		return (List<Role>) roleRepository.findAll();
	}

}
