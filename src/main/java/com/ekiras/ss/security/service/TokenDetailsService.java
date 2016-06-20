package com.ekiras.ss.security.service;

import com.ekiras.ss.security.storage.TokenStorageService;
import com.ekiras.ss.security.token.RestAuthenticationToken;

/**
 * @author ekansh
 * @since 7/4/16
 */
public interface TokenDetailsService {

    RestAuthenticationToken loadUserByToken(String token);
    void setTokenStorageService(TokenStorageService tokenStorageService);
}
