package com.skt.mvc.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Sample2Controller {
    @GetMapping("/sample2")
    public String sample() {
        return "sample2";
    }
}
