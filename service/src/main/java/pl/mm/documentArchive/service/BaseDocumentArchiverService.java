package pl.mm.documentArchive.service;

import org.springframework.transaction.annotation.Transactional;
import pl.mm.documentArchive.daoRepository.TransactionInfoRepository;
import pl.mm.documentArchive.model.TransactionInfo;

import java.sql.Timestamp;
import java.util.UUID;

public abstract class BaseDocumentArchiverService<T> implements IBasicDocumentArchiverService<T> {

	protected TransactionInfoRepository<T, Long> repository;

	@SuppressWarnings({"unchecked"})
	protected T setDefaultVariables(TransactionInfo object) {
		object.setUuid(UUID.randomUUID().toString());
		object.setCreatedDateTime(new Timestamp(System.currentTimeMillis()));
		return (T) object;
	}

	@Transactional
	@Override
	public T findByUuid(String uuid, Class<T> clazz) {
		return repository.findByUuid(uuid, clazz).orElse(null);
	}

}
