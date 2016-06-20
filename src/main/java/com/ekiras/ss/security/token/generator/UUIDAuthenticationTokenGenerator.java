package com.ekiras.ss.security.token.generator;

import java.util.UUID;

/**
 * @author ekansh
 * @since 7/4/16
 */
public class UUIDAuthenticationTokenGenerator implements TokenGenerator {

    @Override
    public String generateToken() {
        return UUID.randomUUID().toString();
    }
}
