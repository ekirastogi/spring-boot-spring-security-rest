package com.ekiras.ss.security.filter;

import com.ekiras.ss.security.storage.TokenStorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

/**
 * @author ekansh
 * @since 13/4/16
 */
public abstract class RestAbstractAuthenticationProcessingFilter extends AbstractAuthenticationProcessingFilter{


    protected static Logger logger = LoggerFactory.getLogger(RestAbstractAuthenticationProcessingFilter.class);

    protected RestAbstractAuthenticationProcessingFilter(String defaultFilterProcessesUrl) {
        super(defaultFilterProcessesUrl);
    }

    public abstract void setUserDetailsService(UserDetailsService userDetailsService);
    public abstract void setTokenStorageService(TokenStorageService tokenStorageService);

}
