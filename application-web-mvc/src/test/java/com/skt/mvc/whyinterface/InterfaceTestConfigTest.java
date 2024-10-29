package com.skt.mvc.whyinterface;

import org.junit.jupiter.api.Test;

class InterfaceTestConfigTest {

    @Test
    void name() {
        String input = "test";
        InterfaceService4 interfaceService4 = InterfaceTestConfig.getInterfaceService4();

        interfaceService4.myApplication(input);
    }
}