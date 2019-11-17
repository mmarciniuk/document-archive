package pl.mm.documentArchive.webApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;
import pl.mm.documentArchive.serviceConfig.ServiceDefaultConfiguration;

@EnableScheduling
@Import({ServiceDefaultConfiguration.class})
@SpringBootApplication
public class Main {

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}

}
