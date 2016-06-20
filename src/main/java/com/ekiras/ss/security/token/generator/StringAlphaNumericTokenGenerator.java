package com.ekiras.ss.security.token.generator;

import org.apache.commons.lang3.RandomStringUtils;

/**
 * @author ekansh
 * @since 13/4/16
 */
public class StringAlphaNumericTokenGenerator implements TokenGenerator {

    @Override
    public String generateToken() {
        return RandomStringUtils.randomAlphanumeric(32);
    }

}
