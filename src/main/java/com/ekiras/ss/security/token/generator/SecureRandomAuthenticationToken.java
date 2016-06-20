package com.ekiras.ss.security.token.generator;

import java.math.BigInteger;
import java.security.SecureRandom;

/**
 * @author ekansh
 * @since 7/4/16
 */
public class SecureRandomAuthenticationToken implements TokenGenerator {

    private SecureRandom random = new SecureRandom();

    @Override
    public String generateToken(){
        return new BigInteger(164, random).toString(16);
    }
}