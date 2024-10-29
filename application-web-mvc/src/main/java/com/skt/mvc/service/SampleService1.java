package com.skt.mvc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SampleService1 {
    @Autowired
    private SampleService2 sampleService2;

    public String sample() {
        return "sample";
    }
}
