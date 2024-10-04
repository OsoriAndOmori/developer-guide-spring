package com.skt.mvc.factory;

import com.skt.autoconfigure.AutoImportSelector;
import com.skt.autoconfigure.annotation.자동설정사용해보자;
import com.skt.mvc.annotation.MyApplication;
import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.startup.Tomcat;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;

public class MyApplicationFactory {
    public static void run(Class servletApplicationClass, String[] args) throws LifecycleException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        //tomcat run
        Tomcat tomcat = new Tomcat();
        Connector connector = new Connector();
        connector.setPort(8080);
        tomcat.setConnector(connector);
        Context context = tomcat.addContext("", "/");

        AnnotationConfigWebApplicationContext ac = new AnnotationConfigWebApplicationContext();
        ac.register(servletApplicationClass);

        Annotation annotation = servletApplicationClass.getAnnotation(MyApplication.class);
        // 자동설정 되어있는지 확인
        if (annotation.annotationType().isAnnotationPresent(자동설정사용해보자.class)) {
            자동설정사용해보자 자동설정 = annotation.annotationType().getAnnotation(자동설정사용해보자.class);
            AutoImportSelector autoImportSelector = 자동설정.value().getConstructor().newInstance();
            String[] 목록 = autoImportSelector.자동설정목록가져와();

            // 목록을 하나씩 빈 등록해보자
            for (String 등록대상 : 목록) {
                Class<?> configClass = Class.forName(등록대상);
                ac.register(configClass);
            }
        }
        ac.refresh();

        // DispatcherServlet 에다가 Spring container 넣어서 tomcat 셋팅
        DispatcherServlet dispatcherServlet = new DispatcherServlet(ac);
        tomcat.addServlet("", "dispatcherServlet", dispatcherServlet);
        context.addServletMappingDecoded("/", "dispatcherServlet");

        tomcat.start();
    }
}
