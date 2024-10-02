package com.skt.mvc.servlet;

import com.skt.mvc.config.ControllerAppConfig;
import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.startup.Tomcat;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class ServletApplication {
    public static void main(String[] args) throws LifecycleException {
        //tomcat run
        Tomcat tomcat = new Tomcat();
        Connector connector = new Connector();
        connector.setPort(8080);
        tomcat.setConnector(connector);
        Context context = tomcat.addContext("", "/");

        tomcat.addServlet("", "testServlet", new TestServlet());
        context.addServletMappingDecoded("/test-servlet", "testServlet");

        tomcat.addServlet("", "testServlet2", new TestServlet());
        context.addServletMappingDecoded("/test2-servlet", "testServlet2");

        AnnotationConfigWebApplicationContext ac = new AnnotationConfigWebApplicationContext();
        ac.register(ControllerAppConfig.class);
        //ac.scan("com.skt.mvc");
        DispatcherServlet dispatcherServlet = new DispatcherServlet(ac);

        tomcat.addServlet("", "dispatcherServlet", dispatcherServlet);
        context.addServletMappingDecoded("/", "dispatcherServlet");


        tomcat.start();
    }
}
