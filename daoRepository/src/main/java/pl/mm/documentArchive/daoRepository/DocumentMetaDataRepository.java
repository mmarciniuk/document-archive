package pl.mm.documentArchive.daoRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.mm.documentArchive.model.DocumentMetaData;

import java.util.Optional;

@Repository(DocumentMetaDataRepository.BEAN_NAME)
public interface DocumentMetaDataRepository extends TransactionInfoRepository<DocumentMetaData, Long> {

	String BEAN_NAME = "documentMetaDataRepository";

	@Query("SELECT dmd " +
			"FROM DocumentMetaData dmd " +
			"WHERE dmd.name=:name " +
			"AND dmd.document.documentName=:documentName " +
			"AND dmd.document.documentOwner.userName=:userName")
	Optional<DocumentMetaData> findByNameAndDocumentAndUserName(@Param("name")String name,
	                                                            @Param("documentName")String documentName,
	                                                            @Param("userName") String userName);

}
