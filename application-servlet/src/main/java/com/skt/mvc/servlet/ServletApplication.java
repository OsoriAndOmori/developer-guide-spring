package com.skt.mvc.servlet;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.startup.Tomcat;

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
        
        tomcat.start();
    }
}
