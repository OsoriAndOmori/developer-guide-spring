package com.skt.autoconfigure.match;

import com.skt.autoconfigure.annotation.MyPropertyCondition;
import org.springframework.core.env.Environment;

import java.util.Optional;

/**
 * OnBeanCondition 의 역할
 */
public class PropertyFiltering implements FilteringMyCondition {
    @Override
    public boolean match(Class<?> configClass, Environment environment) {
        MyPropertyCondition annotation = configClass.getAnnotation(MyPropertyCondition.class);
        String target = Optional.ofNullable(environment.getProperty(annotation.target())).orElse("");
        String value = annotation.value();

        return target.equals(value);
    }
}
