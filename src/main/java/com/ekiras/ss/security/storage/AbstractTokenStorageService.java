package com.ekiras.ss.security.storage;

import org.springframework.security.core.Authentication;

/**
 * @author ekansh
 * @since 13/4/16
 */
public abstract class AbstractTokenStorageService implements TokenStorageService {

    public void persistAndCacheToken(Authentication authentication){
        persistToken(authentication);
        cacheToken(authentication);
    }
}
