package com.skt.mvc.service;

import com.skt.mvc.model.request.SampleRequestParam;
import com.skt.mvc.model.response.SampleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SampleService1 {
    @Autowired
    private SampleService2 sampleService2;

    public String sample() {
        return "sample";
    }

    public SampleResponse sample2(SampleRequestParam param) {
        return new SampleResponse();
    }
}
