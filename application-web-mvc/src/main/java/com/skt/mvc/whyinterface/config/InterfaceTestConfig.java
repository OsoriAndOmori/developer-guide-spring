package com.skt.mvc.whyinterface.config;

import com.skt.mvc.whyinterface.*;
import com.skt.mvc.whyinterface.noti.MailSendPolicy;
import com.skt.mvc.whyinterface.noti.SendPolicyInterface;
import com.skt.mvc.whyinterface.pre.PreProcessInterface;
import com.skt.mvc.whyinterface.process.ProcessDataInterface;

public class InterfaceTestConfig {
    public static InterfaceService4 getInterfaceService4() {
        PreProcessInterface pre = first -> {};
        ProcessDataInterface proc = second -> {};
        SendPolicyInterface sendPolicyInterface = new MailSendPolicy();

        return new InterfaceService4(pre, proc, sendPolicyInterface);
    }
}
