package pl.mm.documentArchive.daoRepository;

import org.springframework.stereotype.Repository;
import pl.mm.documentArchive.model.Document;

@Repository(DocumentRepository.BEAN_NAME)
public interface DocumentRepository extends TransactionInfoRepository<Document, Long> {

	String BEAN_NAME = "documentRepository";

}
