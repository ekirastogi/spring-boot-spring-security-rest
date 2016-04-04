package com.ekiras.ss.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author ekansh
 * @since 30/3/16
 */
@Controller
@RequestMapping({"","/"})
public class HomeController {

    private static final String path = "/";

    @RequestMapping("")
    public String home(){
        return path+"index";
    }

    @RequestMapping("/authorities")
    @ResponseBody
    public Object authorities(){
        return SecurityContextHolder.getContext().getAuthentication().getAuthorities();
    }

}
