package pl.mm.documentArchive.daoRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.mm.documentArchive.model.User;

import java.util.Optional;

@Repository(UserRepository.BEAN_NAME)
public interface UserRepository extends TransactionInfoRepository<User, Long> {

	String BEAN_NAME = "userRepository";

	@Query("SELECT u FROM User u WHERE u.userName = :userName")
	Optional<User> findByUserName(@Param("userName")String userName);

}
