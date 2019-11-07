package pl.mm.documentArchive.service.document;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.mm.documentArchive.daoRepository.DocumentRepository;
import pl.mm.documentArchive.model.Document;
import pl.mm.documentArchive.model.User;
import pl.mm.documentArchive.service.BaseDocumentArchiverService;
import pl.mm.documentArchive.service.user.UserService;

import java.util.List;

@Service(DocumentService.BEAN_NAME)
public class DocumentService extends BaseDocumentArchiverService<Document> {

	@SuppressWarnings("WeakerAccess")
	public static final String BEAN_NAME = "documentService";

	@Autowired
	private UserService userService;

	@Autowired
	public DocumentService(DocumentRepository documentRepository) {
		this.repository = documentRepository;
	}

	@Transactional
	public Document findByDocumentName(String documentName) {
		return ((DocumentRepository) repository).findByDocumentName(documentName).orElse(null);
	}

	@SuppressWarnings("WeakerAccess")
	@Transactional
	public Document findByDocumentNameAndUserName(String documentName, String userName) {
		return ((DocumentRepository) repository).findByDocumentNameAndUserName(documentName, userName).orElse(null);
	}

	@Transactional
	public List<Document> findAllDocuments(String userName) {
		return (List<Document>) ((DocumentRepository) repository).findAllDocuments(userName);
	}

	@Transactional
	public void addDocument(Document document) throws IncorrectOwnerOfDocument {
		User documentOwner = userService.findByUserName(document.getDocumentOwner().getUserName());
		if (documentOwner == null) {
			throw new IncorrectOwnerOfDocument(document.getDocumentOwner().getUserName());
		} else {
			document.setDocumentOwner(documentOwner);
			document = setDefaultVariables(document);
			repository.save(document);
		}
	}

	@Transactional
	public void deleteDocument(Document document) {
		Document documentToRemove = findByDocumentNameAndUserName(document.getDocumentName(), document.getDocumentOwner().getUserName());
		repository.delete(documentToRemove);
	}

}
