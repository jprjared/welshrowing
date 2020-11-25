package com.team1.welshrowing.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    /**
     * Adds in-memory test users for easy access
     * Adapted from code examples at https://www.baeldung.com/spring-security-login [Accessed: 20 Nov 2020]
     * @param auth - An AuthenticationManagerBuilder object
     * @throws Exception
     */
    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user").password(passwordEncoder().encode("pass")).roles("USER")
                .and()
                .withUser("admin").password(passwordEncoder().encode("pass")).roles("ADMIN");
    }

    /**
     * Configure web security requests
     * Adapted from code examples at https://stackoverflow.com/a/53615055/12809235 [Accessed: 20 Nov 2020]
     * @param web - A WebSecurity object
     * @throws Exception
     */
    @Override
    public void configure(final WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/",
                        "/register",
                        "/application",
                        "/css/**/*.css",
                        "/js/**/*.js",
                        "/assets/**"
                );
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}