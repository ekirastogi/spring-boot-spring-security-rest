package com.ekiras.ss.security.validation;

/**
 * @author ekansh
 * @since 7/4/16
 */
public interface TokenValidationService {

    boolean isValidToken(String token);

}
