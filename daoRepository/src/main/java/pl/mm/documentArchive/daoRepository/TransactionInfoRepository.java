package pl.mm.documentArchive.daoRepository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

@NoRepositoryBean
public interface TransactionInfoRepository<T, ID> extends CrudRepository<T, ID> {

	Optional<T> findByUuid(@Param("uuid") String uuid, Class<T> clazz);

}
