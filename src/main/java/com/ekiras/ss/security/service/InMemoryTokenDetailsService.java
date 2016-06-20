package com.ekiras.ss.security.service;

import com.ekiras.ss.security.storage.TokenStorageService;
import com.ekiras.ss.security.token.RestAuthenticationToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.HashSet;
import java.util.Set;

/**
 * @author ekansh
 * @since 7/4/16
 */
public class InMemoryTokenDetailsService implements TokenDetailsService  {

    private static Logger logger = LoggerFactory.getLogger(InMemoryTokenDetailsService.class);

    private TokenStorageService tokenStorageService;

    @Override
    public RestAuthenticationToken loadUserByToken(String token) {
        logger.debug(" load user details by token for token :: " + token);
        RestAuthenticationToken authenticationToken = tokenStorageService.getDetailsByToken(token);
        if(authenticationToken!=null){
            return authenticationToken;
        }
        logger.debug(" user not found with the provided token, returning null");
        return null;
    }

    @Override
    public void setTokenStorageService(TokenStorageService tokenStorageService) {

        System.out.println("################   set token storage service");
        this.tokenStorageService=tokenStorageService;
    }

    private Set<GrantedAuthority> loadAuthorities(){
        Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("USER"));
        authorities.add(new SimpleGrantedAuthority("MODERATOR"));
        return authorities;
    }


}
