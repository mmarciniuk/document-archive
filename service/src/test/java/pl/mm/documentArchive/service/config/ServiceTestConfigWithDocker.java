package pl.mm.documentArchive.service.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

@Profile("TestServiceWithDocker")
@PropertySource("/config/testConfiguration-TestWithDocker.properties")
@Configuration
public class ServiceTestConfigWithDocker {
}
