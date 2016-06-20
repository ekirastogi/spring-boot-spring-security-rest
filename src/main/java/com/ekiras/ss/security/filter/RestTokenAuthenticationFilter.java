package com.ekiras.ss.security.filter;

import com.ekiras.ss.security.service.TokenDetailsService;
import com.ekiras.ss.security.validation.SimpleTokenValidationService;
import com.ekiras.ss.security.validation.TokenValidationService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author ekansh
 * @since 7/4/16
 */

public class RestTokenAuthenticationFilter extends GenericFilterBean {

    private String authenticationHeaderKey = "X-AUTH-TOKEN";

    private TokenValidationService tokenValidationService = new SimpleTokenValidationService();
    private TokenDetailsService tokenDetailsService;

    public RestTokenAuthenticationFilter(){}

    public void setAuthenticationHeaderKey(String headerKey){
        this.authenticationHeaderKey = headerKey;
    }

    public void setTokenValidationService(TokenValidationService tokenValidationService){
        this.tokenValidationService = tokenValidationService;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        String token = getAuthenticationToken(request);
        if(tokenValidationService.isValidToken(token)){
            Authentication authentication = tokenDetailsService.loadUserByToken(getAuthenticationToken(request));
            if(authentication!=null){
                logger.debug("user details successfully fetched, authenticating user with authentication :: " + authentication);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        chain.doFilter(request,response);
    }


    public String getAuthenticationToken(ServletRequest servletRequest){
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        return request.getHeader(authenticationHeaderKey);
    }

    public void setTokenDetailsService(TokenDetailsService tokenDetailsService) {
        this.tokenDetailsService = tokenDetailsService;
    }

    @Override
    public void destroy() {

    }
}
