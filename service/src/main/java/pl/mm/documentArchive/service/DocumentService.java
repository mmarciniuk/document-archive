package pl.mm.documentArchive.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.mm.documentArchive.daoRepository.DocumentRepository;
import pl.mm.documentArchive.model.Document;
import pl.mm.documentArchive.model.User;

@Service(DocumentService.BEAN_NAME)
public class DocumentService extends BaseDocumentArchiverService<Document> {

	@SuppressWarnings("WeakerAccess")
	public static final String BEAN_NAME = "documentService";

	@Autowired
	public DocumentService(DocumentRepository documentRepository) {
		this.repository = documentRepository;
	}

	public Document findByDocumentName(String documentName){
		return ((DocumentRepository)repository).findByDocumentName(documentName).orElse(null);
	}

	@SuppressWarnings("WeakerAccess")
	public Document findByDocumentNameAndUserName(String documentName, String userName) {
		return ((DocumentRepository)repository).findByDocumentNameAndUserName(documentName, userName).orElse(null);
	}

	public void addDocument(Document document) {
		document = setDefaultVariables(document);
		repository.save(document);
	}

	public void deleteDocument(Document document, User user) {
		Document documentToRemove = findByDocumentNameAndUserName(document.getDocumentName(), user.getUserName());
		((DocumentRepository)repository).delete(document);
	}

}
