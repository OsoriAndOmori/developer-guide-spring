package com.skt.mvc.whyinterface;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InterfaceService4 {
    private final PreProcessInterface preProcessInterface; //이거 수미님이 구현하세요
    private final ProcessDataInterface processDataInterface; //이거 영훈님
    private final SendPolicyInterface sendPolicyInterface;

    public void myApplication(String input) {
        preProcessInterface.preprocess(input);
        processDataInterface.now_process(input);
        sendPolicyInterface.send(input);
    }
}
