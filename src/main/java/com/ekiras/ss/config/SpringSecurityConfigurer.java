package com.ekiras.ss.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author ekansh
 * @since 30/3/16
 */
@EnableWebSecurity
public class SpringSecurityConfigurer extends WebSecurityConfigurerAdapter{


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/user/**").fullyAuthenticated()
                .anyRequest().authenticated()
            .and()
            .formLogin()
            .and()
            .logout()
        ;
    }
}
