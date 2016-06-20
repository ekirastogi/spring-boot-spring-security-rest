package com.ekiras.ss.security.token.generator;

import java.util.Date;
import java.util.UUID;

/**
 * @author ekansh
 * @since 13/4/16
 */
public class TimestampTokenGenerator implements TokenGenerator {

    @Override
    public String generateToken() {
        return new Date().getTime() +"-"+ UUID.randomUUID().toString();
    }
}
