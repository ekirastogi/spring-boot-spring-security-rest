package com.ekiras.ss.security.handler;

import com.ekiras.ss.security.core.RestAuthenticationPayload;
import com.ekiras.ss.security.token.RestAuthenticationToken;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author ekansh
 * @since 14/4/16
 */
public class DefaultRestSuccessHandler extends AbstractRestSuccessHandler {

    @Override
    protected RestAuthenticationPayload createResponse(HttpServletRequest request, HttpServletResponse response, RestAuthenticationToken authentication) {
        logger.debug("writing response for authenticated request now");
        return authentication.getPayload();
    }

}
