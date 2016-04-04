package com.ekiras.ss.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author ekansh
 * @since 30/3/16
 */
@Controller
@RequestMapping("/admin")
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminController {

    private static final String path = "/admin/";

    @RequestMapping({"","/"})
    public String home(){
        return path+"index";
    }
}
