package pl.mm.documentArchive.service.document;

public class IncorrectOwnerOfDocument extends Exception {

	public IncorrectOwnerOfDocument(String userName) {
		super("Incorrect owner of document: userName [" + userName + "].");
	}

}
