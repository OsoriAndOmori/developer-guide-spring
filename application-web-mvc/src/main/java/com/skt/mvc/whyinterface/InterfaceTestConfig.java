package com.skt.mvc.whyinterface;

public class InterfaceTestConfig {
    public static InterfaceService4 getInterfaceService4() {
        PreProcessInterface pre = first -> {};
        ProcessDataInterface proc = second -> {};
        SendPolicyInterface sendPolicyInterface = new MailSendPolicy();

        return new InterfaceService4(pre, proc, sendPolicyInterface);
    }
}
