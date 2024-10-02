package com.skt.mvc.factory;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.startup.Tomcat;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class MyApplicationFactory {
    public static void run(Class servletApplicationClass, String[] args) throws LifecycleException {
        //tomcat run
        Tomcat tomcat = new Tomcat();
        Connector connector = new Connector();
        connector.setPort(8080);
        tomcat.setConnector(connector);
        Context context = tomcat.addContext("", "/");

        AnnotationConfigWebApplicationContext ac = new AnnotationConfigWebApplicationContext();
        ac.register(servletApplicationClass);
        DispatcherServlet dispatcherServlet = new DispatcherServlet(ac);

        tomcat.addServlet("", "dispatcherServlet", dispatcherServlet);
        context.addServletMappingDecoded("/", "dispatcherServlet");

        tomcat.start();
    }
}
