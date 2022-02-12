package com.pdp.reactors.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @Autowired
    Environment environment;

    @GetMapping("/")
    public String hello(){
        return  "app is working";
    }


}
