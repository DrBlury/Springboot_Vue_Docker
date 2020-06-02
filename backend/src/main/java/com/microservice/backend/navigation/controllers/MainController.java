package com.microservice.backend.navigation.controllers;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class MainController {

    Logger log = LoggerFactory.getLogger(MainController.class);

    @RequestMapping("/camunda")
    public String camunda(){
        System.out.println("Got a request!!!");

        return "Camunda hallo!";
    }

    @RequestMapping("/test")
    public String test(){
        System.out.println("Got a request2!!!");
        return "Test hallo!";
    }
}
