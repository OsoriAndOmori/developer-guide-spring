package com.skt.mvc.controller;

import com.skt.mvc.model.request.SampleRequestParam;
import com.skt.mvc.model.response.SampleResponse;
import com.skt.mvc.service.SampleService1;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SampleController {
    private final SampleService1 sampleService1;

    @GetMapping("/sample")
    public SampleResponse sample(SampleRequestParam param) {
        return SampleResponse.of(sampleService1.sample2(param.getId()));
    }

    //파라미터 검증
    //넘길 service 호출
    //Response making 저는 2~3줄 선호함

    // 근데 사람마다 다름 제 사수는 controller 한줄로 짜고 다 Service 에서 만들어오라고함.
    // 파라미터 검증은 BindingResult 로 하면되는거고, Response 모델을 Application 서비스에서 만들거나.
    // Domain services vs Application services


}
