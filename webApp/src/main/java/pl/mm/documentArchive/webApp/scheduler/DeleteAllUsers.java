package pl.mm.documentArchive.webApp.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pl.mm.documentArchive.model.db.User;
import pl.mm.documentArchive.service.user.UserService;

import java.util.List;

@Component
public class DeleteAllUsers {

	private static final Logger LOGGER = LoggerFactory.getLogger(DeleteAllUsers.class);

	@Autowired
	private UserService userService;

	@Scheduled(/*cron = "0 0 0 1/1 * ? *"*/ fixedDelay = 600000)
	public void deleteAllUsers() {
		List<User> userList = userService.getAllUsers();
		userList.forEach(user -> {
			try {
				userService.deleteUser(user);
				LOGGER.info("Deleted user " + user.getUserName());
			} catch (Exception e) {
				LOGGER.error(e.toString());
			}
		});

	}

}
