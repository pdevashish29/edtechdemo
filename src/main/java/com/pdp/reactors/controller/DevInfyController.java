package com.pdp.reactors.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DevInfyController {

    @GetMapping("/dev-infy")
    public String sayHelloApi(){
        return  "Wah kya baat h";
    }

}
