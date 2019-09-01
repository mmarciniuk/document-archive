package pl.mm.documentArchive.service.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@EnableAspectJAutoProxy(proxyTargetClass = true)
@ComponentScan({"pl.mm.documentArchive.service"})
@EnableJpaRepositories(basePackages = "pl.mm.documentArchive.daoRepository")
@EnableTransactionManagement
@Import({ServiceTestConfigWithDocker.class})
@PropertySource("/config/testConfiguration.properties")
@Configuration
public class ServiceTestConfig {

	@Autowired
	private Environment environment;

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public DataSource dataSource() {
		return DataSourceBuilder.create()
				.url(environment.getProperty("spring.datasource.url"))
				.username(environment.getProperty("spring.datasource.username"))
				.password(environment.getProperty("spring.datasource.password"))
				.build();
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder,
	                                                                   @Autowired DataSource dataSource) {
		return builder.dataSource(dataSource)
				.packages("pl.mm.documentArchive.model")
				.persistenceUnit("documentArchiveModel")
				.build();
	}

	@Bean
	public PlatformTransactionManager transactionManager(@Autowired EntityManagerFactory entityManagerFactory) {
		return new JpaTransactionManager(entityManagerFactory);
	}



}
