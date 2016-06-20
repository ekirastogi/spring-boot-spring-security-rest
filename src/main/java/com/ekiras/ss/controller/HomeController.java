package com.ekiras.ss.controller;

import com.ekiras.ss.security.token.generator.SecureRandomAuthenticationToken;
import com.ekiras.ss.security.token.generator.TimestampTokenGenerator;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.List;

/**
 * @author ekansh
 * @since 30/3/16
 */
@RestController
@RequestMapping({"","/"})
public class HomeController {

    @RequestMapping("")
    public String home(){
        return "Hello :: This is app home";
    }

    @RequestMapping("token")
    public List<String> token(){


        SecureRandomAuthenticationToken randomAuthenticationToken = new SecureRandomAuthenticationToken();
        TimestampTokenGenerator timestampTokenGenerator = new TimestampTokenGenerator();
        List<String> tokens = new LinkedList<String>();


        for(int i=0;i<10;i++) {
            String t = randomAuthenticationToken.generateToken();
            tokens.add( t + " :: " + t.length());
        }
        tokens.add("#####################################################");
        for(int i=0;i<10;i++) {
            String t = timestampTokenGenerator.generateToken();
            tokens.add( t + " :: " + t.length());
        }

        tokens.add("#####################################################");

        return tokens;
    }

}
