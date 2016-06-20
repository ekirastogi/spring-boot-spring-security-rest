package com.ekiras.ss.security.handler;

import com.ekiras.ss.security.token.RestAuthenticationToken;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author ekansh
 * @since 15/4/16
 */
public abstract class AbstractRestSuccessHandler implements RestSuccessHandler{

    protected static Logger logger = LoggerFactory.getLogger(RestSuccessHandler.class);

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        System.out.println("############### successs handler called");
        logger.debug(" Authentication successful for request " + request.toString());


        Object responseData = createResponse(request,response, (RestAuthenticationToken) authentication);
        System.out.println("################    auth data in success handler :: " + ((RestAuthenticationToken) authentication).getPayload().toString());
        if(responseData==null)
            responseData="OK";
        else{
            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            responseData = ow.writeValueAsString(responseData);
        }
        response.setContentType("application/json");
        response.getWriter().write(responseData.toString());
    }


    protected abstract Object createResponse(HttpServletRequest request, HttpServletResponse response, RestAuthenticationToken authentication);
}
