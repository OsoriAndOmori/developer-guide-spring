package com.skt.mvc.whyinterface.noti;

import org.springframework.stereotype.Component;

@Component
public class MailSendPolicy implements SendPolicyInterface {
    @Override
    public void send(String Third) {
        System.out.println("Mail Send Policy");
    }
}
