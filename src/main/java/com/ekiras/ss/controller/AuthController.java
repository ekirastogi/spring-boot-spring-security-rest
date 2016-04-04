package com.ekiras.ss.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author ekansh
 * @since 5/4/16
 */
@Controller
@RequestMapping("/auth")
public class AuthController {

    @RequestMapping("/login")
    public String login(){
        System.out.println("login request ");
        return "/auth/login";
    }
}
