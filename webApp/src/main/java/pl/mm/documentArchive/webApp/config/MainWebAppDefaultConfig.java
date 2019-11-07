package pl.mm.documentArchive.webApp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@PropertySource("classpath:application.properties")
@Configuration
public class MainWebAppDefaultConfig implements WebMvcConfigurer {



}
