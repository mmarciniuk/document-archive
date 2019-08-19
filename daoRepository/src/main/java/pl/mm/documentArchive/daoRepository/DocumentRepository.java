package pl.mm.documentArchive.daoRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.mm.documentArchive.model.Document;

import java.util.Optional;

@Repository(DocumentRepository.BEAN_NAME)
public interface DocumentRepository extends TransactionInfoRepository<Document, Long> {

	String BEAN_NAME = "documentRepository";

	@Query("SELECT d FROM Document d WHERE d.documentName=:documentName")
	Optional<Document> findByDocumentName(@Param("documentName") String documentName);

}
