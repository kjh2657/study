package com.aroo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class ApiController {

    @GetMapping("/test")
    public String test(String param){
        log.info("param = {}", param);
        log.info("Server test call");
        return "hello";
    }
}
