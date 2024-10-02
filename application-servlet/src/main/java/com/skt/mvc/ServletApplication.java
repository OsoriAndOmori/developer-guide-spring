package com.skt.mvc;

import com.skt.mvc.annotation.MyApplication;
import com.skt.mvc.factory.MyApplicationFactory;
import org.apache.catalina.LifecycleException;

@MyApplication
public class ServletApplication {
    public static void main(String[] args) throws LifecycleException {
        MyApplicationFactory.run(ServletApplication.class, args);
    }
}
