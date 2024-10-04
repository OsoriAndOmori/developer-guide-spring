package com.skt.autoconfigure.match;

import org.springframework.core.env.Environment;

/**
 * FilteringSpringBootCondition 의 역할
 */
public interface FilteringMyCondition {
    boolean match(Class<?> configClass, Environment environment);
}
