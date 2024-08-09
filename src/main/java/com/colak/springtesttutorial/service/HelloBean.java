package com.colak.springtesttutorial.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class HelloBean {

    public void sayHello() {
        log.info("say hello");
    }
}
