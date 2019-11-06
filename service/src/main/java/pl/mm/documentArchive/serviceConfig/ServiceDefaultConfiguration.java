package pl.mm.documentArchive.serviceConfig;

import org.springframework.context.annotation.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import pl.mm.documentArchive.daoConfig.JpaDefaultConfiguration;

@EnableAspectJAutoProxy(proxyTargetClass = true)
@Import({JpaDefaultConfiguration.class})
@ComponentScan(basePackages = {"pl.mm.documentArchive.service"})
@Configuration
public class ServiceDefaultConfiguration {

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
