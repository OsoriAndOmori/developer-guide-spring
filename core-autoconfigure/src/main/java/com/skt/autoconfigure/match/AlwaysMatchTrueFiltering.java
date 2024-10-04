package com.skt.autoconfigure.match;

import org.springframework.core.env.Environment;

/**
 * OnBeanCondition 의 역할
 */
public class AlwaysMatchTrueFiltering implements FilteringMyCondition {
    @Override
    public boolean match(Class<?> configClass, Environment environment) {
        return true;
    }
}
