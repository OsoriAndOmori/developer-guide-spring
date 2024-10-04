package com.skt.mvc;

import com.skt.mvc.annotation.MyApplication;
import com.skt.mvc.factory.MyApplicationFactory;
import org.apache.catalina.LifecycleException;

import java.lang.reflect.InvocationTargetException;

@MyApplication
public class ServletApplication {
    public static void main(String[] args) throws LifecycleException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        MyApplicationFactory.run(ServletApplication.class, args);
    }
}
