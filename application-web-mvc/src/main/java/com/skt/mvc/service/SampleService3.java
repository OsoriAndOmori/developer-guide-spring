package com.skt.mvc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SampleService3 {
    @Autowired
    private SampleService1 sampleService1;

    public String sample() {
        return "sample";
    }
}
