package main.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.InMemoryUserDetailsManagerConfigurer;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Created by Michal
 * 2015-01-24.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        InMemoryUserDetailsManagerConfigurer<AuthenticationManagerBuilder> authenticationManagerBuilderInMemoryUserDetailsManagerConfigurer = auth
                .inMemoryAuthentication();

        authenticationManagerBuilderInMemoryUserDetailsManagerConfigurer
                .withUser("michal").password("kigybamo").roles("USER", "ADMIN");
        authenticationManagerBuilderInMemoryUserDetailsManagerConfigurer
                .withUser("mateusz").password("poke000").roles("USER");
    }
}
