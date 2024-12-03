package com.skt.mvc.controller;

import com.skt.mvc.model.request.SampleRequestParam;
import com.skt.mvc.model.response.SampleResponse;
import com.skt.mvc.service.SampleService1;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.core.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
@RequiredArgsConstructor
public class SampleController {
    private final SampleService1 sampleService1;
    private final ConfigurableApplicationContext applicationContext;

    @GetMapping("/sample")
    public SampleResponse sample(SampleRequestParam param) {
        return SampleResponse.of(sampleService1.sample2(param.getId()));
    }

    @PostMapping("/sample/{success}")
    public String sample2(@PathVariable String success) throws InterruptedException {
        sampleService1.sample3(success);
        return "Success";
    }

    @PostMapping("/sample/primitive/{success}")
    public String sample3(@PathVariable String success) throws InterruptedException, SQLException {
        sampleService1.sample4(success);
        return "Success";
    }

    @GetMapping("/beans")
    public String[] beans() throws InterruptedException {
        return applicationContext.getBeanDefinitionNames();
    }


    //파라미터 검증
    //넘길 service 호출
    //Response making 저는 2~3줄 선호함

    // 근데 사람마다 다름 제 사수는 controller 한줄로 짜고 다 Service 에서 만들어오라고함.
    // 파라미터 검증은 BindingResult 로 하면되는거고, Response 모델을 Application 서비스에서 만들거나.
    // Domain services vs Application services


}
