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

	@Query("SELECT d FROM Document d WHERE d.documentOwner.userName=:userName")
	Iterable<Document> findAllDocuments(@Param("userName") String userName);

	@Query("SELECT d FROM Document d WHERE d.documentName=:documentName AND d.documentOwner.userName=:userName")
	Optional<Document> findByDocumentNameAndUserName(@Param("documentName") String documentName,
	                                                 @Param("userName") String userName);

}
