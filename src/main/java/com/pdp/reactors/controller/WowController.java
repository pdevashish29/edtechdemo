package com.pdp.reactors.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WowController {


    @GetMapping("/wow")
    public String getWow(){
        return "Noida Sector -18";
    }
}
