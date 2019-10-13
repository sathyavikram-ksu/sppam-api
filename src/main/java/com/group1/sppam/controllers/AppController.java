package com.group1.sppam.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/app")
public class AppController {

    @RequestMapping("/ping")
    public String ping() {
        return "pong";
    }
}
