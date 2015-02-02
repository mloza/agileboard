package main;

import main.configuration.AppConfig;
import main.controllers.Main;
import main.repositories.StoryRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

/**
 * Created by Michal
 * 2015-01-23.
 */
@EnableAutoConfiguration
@ComponentScan(basePackageClasses = {AppConfig.class, Main.class})
@EnableGlobalMethodSecurity()
@EnableJpaRepositories(basePackageClasses = StoryRepository.class)
@Import(RepositoryRestMvcConfiguration.class)
@Configuration
public class Start {
    public static void main(String[] args) {
        SpringApplication.run(Start.class);
    }
}
