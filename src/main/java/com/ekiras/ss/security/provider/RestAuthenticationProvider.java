package com.ekiras.ss.security.provider;

import com.ekiras.ss.security.core.RestAuthenticationPayload;
import com.ekiras.ss.security.core.SimpleRestAuthenticationPayload;
import com.ekiras.ss.security.storage.TokenStorageService;
import com.ekiras.ss.security.token.RestAuthenticationToken;
import com.ekiras.ss.security.token.generator.TokenGenerator;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.HashSet;
import java.util.Set;

/**
 * @author ekansh
 * @since 15/4/16
 */
public class RestAuthenticationProvider implements AuthenticationProvider {

    private UserDetailsService userDetailsService;
    private TokenStorageService tokenStorageService;
    private TokenGenerator tokenGenerator;


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        RestAuthenticationToken restAuthenticationToken = (RestAuthenticationToken) authentication;
        UserDetails userDetails = userDetailsService.loadUserByUsername(restAuthenticationToken.getPrincipal().toString());
        if(userDetails!=null){
            String token = tokenGenerator.generateToken();
            restAuthenticationToken = new RestAuthenticationToken(authenticationPayload(token),authorities());
            tokenStorageService.persistAndCacheToken(restAuthenticationToken);
            return restAuthenticationToken;
        }
        return null;
    }

    private RestAuthenticationPayload authenticationPayload(String token){
        RestAuthenticationPayload payload = new SimpleRestAuthenticationPayload(token);
        return payload;
    }

    private Set<GrantedAuthority> authorities(){
        Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("ADMIN"));
        authorities.add(new SimpleGrantedAuthority("USER"));
        return authorities;
    }


    @Override
    public boolean supports(Class<?> authentication) {
        if(authentication.getClass().isInstance(RestAuthenticationToken.class))
            return true;
        return false;
    }


    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    public void setTokenStorageService(TokenStorageService tokenStorageService) {
        this.tokenStorageService = tokenStorageService;
    }

    public void setTokenGenerator(TokenGenerator tokenGenerator) {
        this.tokenGenerator = tokenGenerator;
    }
}
