package com.skt.mvc.factory;

import com.skt.autoconfigure.AutoImportSelector;
import com.skt.autoconfigure.annotation.MyBooleanCondition;
import com.skt.autoconfigure.annotation.MyPropertyCondition;
import com.skt.autoconfigure.annotation.자동설정사용해보자;
import com.skt.autoconfigure.match.FilteringMyCondition;
import com.skt.mvc.annotation.MyApplication;
import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.startup.Tomcat;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;

public class MyApplicationFactory {
    public static void run(Class servletApplicationClass, String[] args) throws LifecycleException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        // 1. tomcat 생성
        Tomcat tomcat = new Tomcat();
        Connector connector = new Connector();
        connector.setPort(8080);
        tomcat.setConnector(connector);
        Context context = tomcat.addContext("", "/");

        // 2. Spring Container 생성
        AnnotationConfigWebApplicationContext ac = new AnnotationConfigWebApplicationContext();

        // 3. Auto Configure 돌리기
        Annotation annotation = servletApplicationClass.getAnnotation(MyApplication.class);
        if (annotation.annotationType().isAnnotationPresent(자동설정사용해보자.class)) {
            자동설정사용해보자 자동설정 = annotation.annotationType().getAnnotation(자동설정사용해보자.class);
            AutoImportSelector autoImportSelector = 자동설정.value().getConstructor().newInstance();
            // resource/META-INF/lets.auto.configure 에서 패키지 목록 가져오기
            String[] 목록 = autoImportSelector.자동설정목록가져와();

            // 목록을 하나씩 만들어서 Bean 등록해보자
            for (String 등록대상 : 목록) {
                Class<?> configClass = Class.forName(등록대상);

                // Conditional 조건이 있는 경우 match 판단으로 AutoConfigure 등록하고 말고 하자.
                if (configClass.isAnnotationPresent(MyBooleanCondition.class)) {
                    FilteringMyCondition filteringMyCondition = configClass
                            .getAnnotation(MyBooleanCondition.class)
                            .value()
                            .getConstructor()
                            .newInstance();
                    if (filteringMyCondition.match(configClass, ac.getEnvironment())) {
                        ac.register(configClass);
                    }
                } else if (configClass.isAnnotationPresent(MyPropertyCondition.class)) {
                    FilteringMyCondition filteringMyCondition = configClass
                            .getAnnotation(MyPropertyCondition.class)
                            .annotationType()
                            .getAnnotation(MyBooleanCondition.class)
                            .value()
                            .getConstructor()
                            .newInstance();
                    if (filteringMyCondition.match(configClass, ac.getEnvironment())) {
                        ac.register(configClass);
                    }
                } else {
                    ac.register(configClass);
                }
            }
        }

        // 4. 기본 ComponentScan 한 바퀴 돌림
        ac.register(servletApplicationClass);
        ac.refresh();

        // Bean List 출력
        for (String beanName : ac.getBeanDefinitionNames()) System.out.println(beanName);

        // 5. DispatcherServlet 에다가 Spring container 넣어서 tomcat Run
        DispatcherServlet dispatcherServlet = new DispatcherServlet(ac);
        tomcat.addServlet("", "dispatcherServlet", dispatcherServlet);
        context.addServletMappingDecoded("/", "dispatcherServlet");
        tomcat.start();
    }
}
