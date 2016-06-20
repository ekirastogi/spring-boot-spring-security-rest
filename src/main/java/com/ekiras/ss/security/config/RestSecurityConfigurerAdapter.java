package com.ekiras.ss.security.config;

import com.ekiras.ss.security.filter.RestTokenAuthenticationFilter;
import com.ekiras.ss.security.filter.RestUsernamePasswordAuthenticationFilter;
import com.ekiras.ss.security.handler.DefaultRestSuccessHandler;
import com.ekiras.ss.security.handler.RestSuccessHandler;
import com.ekiras.ss.security.provider.RestAuthenticationProvider;
import com.ekiras.ss.security.service.InMemoryTokenDetailsService;
import com.ekiras.ss.security.service.RestUserDetailsService;
import com.ekiras.ss.security.service.TokenDetailsService;
import com.ekiras.ss.security.storage.InMemoryTokenStorageService;
import com.ekiras.ss.security.storage.TokenStorageService;
import com.ekiras.ss.security.token.generator.TokenGenerator;
import com.ekiras.ss.security.token.generator.UUIDAuthenticationTokenGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author ekansh
 * @since 11/4/16
 */
@EnableWebSecurity
@Order(Ordered.LOWEST_PRECEDENCE-100)
public class RestSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

    private String defaultFilterProcessesUrl = "/auth/login";


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.inMemoryAuthentication()
                .withUser("ekansh")
                .password("pwd")
                .authorities("ADMIN","USER");
        auth.eraseCredentials(false);
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected final void configure(HttpSecurity http) throws Exception {
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(restUsernamePasswordAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(restTokenAuthenticationFilter(), RestUsernamePasswordAuthenticationFilter.class);
        configureSecurity(http);
    }

    protected void configureSecurity(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/admin/**").hasAuthority("ADMIN")
                .antMatchers("/user/**").hasAuthority("USER")
                .anyRequest().authenticated()
                .and()
                .formLogin().successHandler(restSuccessHandler()).disable()
                .logout();
    }


    @Bean
    public RestAuthenticationProvider authenticationProvider(){
        RestAuthenticationProvider provider = new RestAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService());
        provider.setTokenGenerator(tokenGenerator());
        provider.setTokenStorageService(tokenStorageService());
        return provider;
    }


    @Bean
    public TokenStorageService tokenStorageService(){
        TokenStorageService tokenStorageService = new InMemoryTokenStorageService();
        return tokenStorageService;
    }

    @Bean
    public TokenGenerator tokenGenerator(){
        TokenGenerator tokenGenerator = new UUIDAuthenticationTokenGenerator();
        return tokenGenerator;
    }

    @Bean
    public RestSuccessHandler restSuccessHandler(){
        RestSuccessHandler successHandler = new DefaultRestSuccessHandler();
        return successHandler;
    }

    @Bean
    public UserDetailsService userDetailsService(){
        RestUserDetailsService restUserDetailsService = new RestUserDetailsService();
        return restUserDetailsService;
    }

    @Bean
    public TokenDetailsService tokenDetailsService(){
        TokenDetailsService tokenDetailsService = new InMemoryTokenDetailsService();
        tokenDetailsService.setTokenStorageService(tokenStorageService());
        return tokenDetailsService;
    }

    @Bean
    public RestTokenAuthenticationFilter restTokenAuthenticationFilter(){
        RestTokenAuthenticationFilter authenticationFilter =  new RestTokenAuthenticationFilter();
        authenticationFilter.setTokenDetailsService(tokenDetailsService());
        return authenticationFilter;
    }

    @Bean
    public RestUsernamePasswordAuthenticationFilter restUsernamePasswordAuthenticationFilter() throws Exception {
        RestUsernamePasswordAuthenticationFilter authenticationFilter = new RestUsernamePasswordAuthenticationFilter(defaultFilterProcessesUrl);
        authenticationFilter.setAuthenticationManager(authenticationManager());
        authenticationFilter.setAuthenticationSuccessHandler(restSuccessHandler());
        return authenticationFilter;
    }


}
