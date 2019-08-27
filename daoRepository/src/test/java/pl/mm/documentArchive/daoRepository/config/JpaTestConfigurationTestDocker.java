package pl.mm.documentArchive.daoRepository.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

@Profile("TestDocker")
@PropertySource("/config/jpaTestConfiguration-TestDocker.properties")
@Configuration
public class JpaTestConfigurationTestDocker {
}
