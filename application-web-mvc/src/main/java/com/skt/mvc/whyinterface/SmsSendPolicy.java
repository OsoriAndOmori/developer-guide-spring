package com.skt.mvc.whyinterface;

import org.springframework.stereotype.Component;

@Component
public class SmsSendPolicy implements SendPolicyInterface {
    @Override
    public void send(String Third) {
        System.out.println("Sms Send");
    }
}
