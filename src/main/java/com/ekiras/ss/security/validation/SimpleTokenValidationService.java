package com.ekiras.ss.security.validation;

/**
 * @author ekansh
 * @since 7/4/16
 */
public class SimpleTokenValidationService implements TokenValidationService {

    @Override
    public boolean isValidToken(String token) {
        if(token == null || token.trim().equals("")){
            return false;
        }
        return true;
    }
}
