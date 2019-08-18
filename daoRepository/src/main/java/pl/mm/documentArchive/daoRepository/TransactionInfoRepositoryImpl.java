package pl.mm.documentArchive.daoRepository;

import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.util.Optional;

@Component
public abstract class TransactionInfoRepositoryImpl<T, ID> implements TransactionInfoRepository<T, ID> {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Optional<T> findByUuid(String uuid, Class<T> clazz) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<T> query = criteriaBuilder.createQuery(clazz);
		Root<T> root = query.from(clazz);

		Path<String> uuidPath = root.get("uuid");
		Predicate predicate = criteriaBuilder.like(uuidPath, uuid);

		query.select(root)
				.where(criteriaBuilder.and(predicate));

		return Optional.ofNullable(entityManager.createQuery(query).getSingleResult());
	}
}
