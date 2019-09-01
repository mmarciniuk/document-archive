package pl.mm.documentArchive.service.document;

public class IncorrectOwnerOfDocument extends Throwable {

	public IncorrectOwnerOfDocument(String userName) {
		super("Incorrect owner of document: userName [" + userName + "].");
	}

}
