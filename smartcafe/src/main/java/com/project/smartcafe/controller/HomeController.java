package com.project.smartcafe.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping(path = "/")
public class HomeController {

    String home() {
        return "Hello World!";
    }

}
