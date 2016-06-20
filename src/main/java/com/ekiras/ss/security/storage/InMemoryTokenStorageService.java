package com.ekiras.ss.security.storage;

import com.ekiras.ss.security.token.RestAuthenticationToken;
import org.springframework.security.core.Authentication;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ekansh
 * @since 13/4/16
 */
public class InMemoryTokenStorageService extends AbstractTokenStorageService {

    private static final Map<String,RestAuthenticationToken> tokenStorage = new HashMap<String, RestAuthenticationToken>();

    @Override
    public RestAuthenticationToken getDetailsByToken(String token) {
        return tokenStorage.get(token);
    }

    @Override
    public void persistToken(Authentication authentication) {
        if(authentication instanceof RestAuthenticationToken) {
            RestAuthenticationToken restAuthenticationToken = (RestAuthenticationToken) authentication;
            tokenStorage.put(restAuthenticationToken.getPayload().getAuthToken(), restAuthenticationToken);
        }
    }

    @Override
    public void cacheToken(Authentication authentication) {

    }


}
