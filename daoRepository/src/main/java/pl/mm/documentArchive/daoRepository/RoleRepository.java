package pl.mm.documentArchive.daoRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.mm.documentArchive.model.Role;

import java.util.List;
import java.util.Optional;

@Repository(RoleRepository.BEAN_NAME)
public interface RoleRepository extends TransactionInfoRepository<Role, Long> {

	String BEAN_NAME = "roleRepository";

	@Query("SELECT r FROM Role r WHERE r.roleName=:roleName")
	Optional<Role> findByRoleName(@Param("roleName") String roleName);
	@Query("SELECT r FROM Role r WHERE r.roleName IN (:listOfRoles)")
	Iterable<Role> findByRoleName(@Param("listOfRoles")List<String> listOfRoles);

}
