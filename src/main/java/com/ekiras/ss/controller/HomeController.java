package com.ekiras.ss.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
