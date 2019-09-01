package pl.mm.documentArchive.daoRepository.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

@Profile("TestDaoRepositoryWithDocker")
@PropertySource("/config/testConfiguration-TestWithDocker.properties")
@Configuration
public class JpaTestConfigurationTestDaoRepositoryWithDocker {
}
