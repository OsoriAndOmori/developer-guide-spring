package com.skt.mvc.controller;

import com.skt.mvc.model.request.SampleRequestParam;
import com.skt.mvc.model.response.SampleResponse;
import com.skt.mvc.service.SampleService1;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.sql.SQLException;

@RestController
@RequiredArgsConstructor
public class SampleViewController {
    private final SampleService1 sampleService1;
    private final ConfigurableApplicationContext applicationContext;

    @GetMapping("/index")
    public ModelAndView sample2323(SampleRequestParam param) {
        SampleResponse sampleResponse = SampleResponse.of(sampleService1.sample2(param.getId()));
        ModelAndView mv = new ModelAndView("index");
        mv.addObject("message", sampleResponse.getNickname());
        return mv;
    }
}
