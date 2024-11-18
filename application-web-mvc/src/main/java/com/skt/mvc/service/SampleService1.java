package com.skt.mvc.service;

import com.skt.mvc.model.entity.SampleEntity;
import com.skt.mvc.model.request.SampleRequestParam;
import com.skt.mvc.model.response.SampleResponse;
import com.skt.mvc.repository.SampleJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SampleService1 {
    private final SampleService2 sampleService2;

    private final SampleJpaRepository sampleJpaRepository;

    public String sample() {
        return "sample";
    }

    public SampleResponse sample2(SampleRequestParam param) {
        SampleEntity sampleEntity = sampleJpaRepository.findById(param.getId()).orElseThrow();

        SampleResponse sampleResponse = new SampleResponse();
        sampleResponse.setNickname(sampleEntity.getName() + sampleEntity.getId());
        return sampleResponse;
    }
}
