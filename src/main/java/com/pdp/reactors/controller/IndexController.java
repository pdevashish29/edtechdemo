package com.pdp.reactors.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @Autowired
    Environment environment;

    @GetMapping("version")
    public String getAppDeployedVersion(){
        return  environment.getProperty("spring.application.version");
    }

    @GetMapping("app-name")
    public String getAppName(){
        return  environment.getProperty("spring.application.name");
    }
}
