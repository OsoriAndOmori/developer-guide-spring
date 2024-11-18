package com.skt.mvc.service;

import com.skt.mvc.model.domain.Sample;
import com.skt.mvc.repository.SampleJpaRepository;
import com.skt.mvc.repository.SampleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SampleService1 {
    private final SampleService2 sampleService2;

    private final SampleRepository sampleRepository;

    public String sample() {
        return "sample";
    }

    public Sample sample2(String id) {
        return sampleRepository.findById(id);
    }
}
