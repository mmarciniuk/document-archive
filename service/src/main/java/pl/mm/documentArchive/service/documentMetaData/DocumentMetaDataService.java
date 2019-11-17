package pl.mm.documentArchive.service.documentMetaData;

import org.springframework.stereotype.Service;
import pl.mm.documentArchive.model.db.DocumentMetaData;
import pl.mm.documentArchive.service.BaseDocumentArchiverService;

@Service(DocumentMetaDataService.BEAN_NAME)
public class DocumentMetaDataService extends BaseDocumentArchiverService<DocumentMetaData> {

	@SuppressWarnings("unused")
	public static final String BEAN_NAME = "documentMetaDataService";



}
