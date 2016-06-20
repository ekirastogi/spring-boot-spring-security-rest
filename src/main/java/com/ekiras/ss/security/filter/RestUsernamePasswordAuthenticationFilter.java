package com.ekiras.ss.security.filter;

import com.ekiras.ss.security.service.RestUserDetailsService;
import com.ekiras.ss.security.storage.InMemoryTokenStorageService;
import com.ekiras.ss.security.storage.TokenStorageService;
import com.ekiras.ss.security.token.RestAuthenticationToken;
import com.ekiras.ss.security.token.generator.TokenGenerator;
import com.ekiras.ss.security.token.generator.UUIDAuthenticationTokenGenerator;
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.util.Assert;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author ekansh
 * @since 13/4/16
 */
public class RestUsernamePasswordAuthenticationFilter extends RestAbstractAuthenticationProcessingFilter {

    public static final String SPRING_SECURITY_FORM_USERNAME_KEY = "username";
    public static final String SPRING_SECURITY_FORM_PASSWORD_KEY = "password";

    private String usernameParameter = SPRING_SECURITY_FORM_USERNAME_KEY;
    private String passwordParameter = SPRING_SECURITY_FORM_PASSWORD_KEY;

    private UserDetailsService userDetailsService= new RestUserDetailsService();
    private TokenStorageService tokenStorageService = new InMemoryTokenStorageService();
    private TokenGenerator tokenGenerator = new UUIDAuthenticationTokenGenerator();

    public RestUsernamePasswordAuthenticationFilter(String defaultFilterProcessesUrl) {
        super(defaultFilterProcessesUrl);

    }

    public void setTokenStorageService(TokenStorageService tokenStorageService) {
        Assert.notNull(userDetailsService, "Token storage  service cannot be null");
        this.tokenStorageService = tokenStorageService;
    }

    @Override
    public void setUserDetailsService(UserDetailsService userDetailsService) {
        Assert.notNull(userDetailsService, "User details service cannot be null");
        this.userDetailsService=userDetailsService;
    }


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        Authentication authenticationToken = details(request);
        return getAuthenticationManager().authenticate(authenticationToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {

        SecurityContextHolder.getContext().setAuthentication(authResult);

        // Fire event
        if (this.eventPublisher != null) {
            eventPublisher.publishEvent(new InteractiveAuthenticationSuccessEvent(authResult, this.getClass()));
        }
        tokenStorageService.persistAndCacheToken(authResult);
        getSuccessHandler().onAuthenticationSuccess(request, response, authResult);

    }


    private Authentication details(HttpServletRequest request){

        String username = request.getParameter(usernameParameter);
        String password = request.getParameter(passwordParameter);

        if(username==null)
            username="";
        if(password==null)
            password="";

        RestAuthenticationToken token = new RestAuthenticationToken(username,password);
        return token;
    }

}
