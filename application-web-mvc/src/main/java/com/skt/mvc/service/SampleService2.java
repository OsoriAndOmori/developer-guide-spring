package com.skt.mvc.service;

import com.skt.mvc.model.domain.Sample;
import com.skt.mvc.repository.SampleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SampleService2 {
    //    @Autowired
//    private SampleService1 sampleService1;
    private final SampleRepository sampleRepository;

    public String sample() {
        return "sample";
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void saveSamples() throws InterruptedException {
        sampleRepository.save(new Sample(System.currentTimeMillis(), "테스트3"));
        Thread.sleep(10);
        sampleRepository.save(new Sample(System.currentTimeMillis(), "테스트4"));
    }
}
