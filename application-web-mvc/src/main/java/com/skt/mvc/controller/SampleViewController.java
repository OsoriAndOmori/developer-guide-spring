package com.skt.mvc.controller;

import com.skt.mvc.model.request.SampleRequestParam;
import com.skt.mvc.model.response.SampleResponse;
import com.skt.mvc.service.SampleService1;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequiredArgsConstructor
public class SampleViewController {
    private final SampleService1 sampleService1;
    private final ConfigurableApplicationContext applicationContext;

    @GetMapping("/ssr-with-eventhandler")
    public ModelAndView sample2323(SampleRequestParam param) {
        SampleResponse sampleResponse = SampleResponse.of(sampleService1.sample2(param.getId()));
        ModelAndView mv = new ModelAndView("ssr-with-eventhandler");
        mv.addObject("message", sampleResponse.getNickname());
        return mv;
    }

    @GetMapping("/ssr-with-csr")
    public ModelAndView sample23232(SampleRequestParam param) {
        SampleResponse sampleResponse = SampleResponse.of(sampleService1.sample2(param.getId()));
        ModelAndView mv = new ModelAndView("ssr-with-csr");
        mv.addObject("message", sampleResponse.getNickname());
        return mv;
    }
}
