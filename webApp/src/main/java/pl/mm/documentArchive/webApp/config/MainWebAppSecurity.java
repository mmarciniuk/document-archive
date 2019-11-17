package pl.mm.documentArchive.webApp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import pl.mm.documentArchive.webApp.viewController.IndexController;
import pl.mm.documentArchive.webApp.viewController.UserController;

@EnableWebSecurity
@Configuration
public class MainWebAppSecurity extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.regexMatchers(IndexController.RequestMappings.BASE).permitAll()
				.regexMatchers(UserController.RequestMappings.BASE + UserController.RequestMappings.REGISTRATION_FROM).permitAll()
				.anyRequest().authenticated()
				.antMatchers("/js/**").permitAll()
				.and()
				.formLogin().loginPage(UserController.RequestMappings.BASE + UserController.RequestMappings.LOGIN_FROM)
				.permitAll()
				.loginProcessingUrl(UserController.RequestMappings.PROCESS_LOGIN).permitAll()
				.and()
				.logout().permitAll()
		;
	}

}
