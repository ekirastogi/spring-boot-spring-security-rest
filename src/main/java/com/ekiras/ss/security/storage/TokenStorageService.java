package com.ekiras.ss.security.storage;

import com.ekiras.ss.security.token.RestAuthenticationToken;
import org.springframework.security.core.Authentication;

/**
 * @author ekansh
 * @since 13/4/16
 */
public interface TokenStorageService {

    RestAuthenticationToken getDetailsByToken(String token);
    void persistAndCacheToken(Authentication authentication);
    void persistToken(Authentication authentication);
    void cacheToken(Authentication authentication);

}
