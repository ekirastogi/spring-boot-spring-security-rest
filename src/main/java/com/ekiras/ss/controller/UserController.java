package com.ekiras.ss.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author ekansh
 * @since 30/3/16
 */
@Controller
@RequestMapping("/user")
@PreAuthorize("hasAuthority('USER')")
public class UserController {

    private static final String path = "/user/";

    @RequestMapping("")
    public String home(){
        return path+"/index";
    }
}
