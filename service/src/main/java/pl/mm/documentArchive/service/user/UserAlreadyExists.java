package pl.mm.documentArchive.service.user;

import pl.mm.documentArchive.model.User;

public class UserAlreadyExists extends Throwable {

	public UserAlreadyExists(User foundUser){
		super("User with userName ["+foundUser.getUserName()+"] already exists.");
	}

}
