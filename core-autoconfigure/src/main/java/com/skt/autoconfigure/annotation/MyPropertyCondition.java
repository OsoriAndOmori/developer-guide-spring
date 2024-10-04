package com.skt.autoconfigure.annotation;

import com.skt.autoconfigure.match.PropertyFiltering;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * ConditionalOnClass
 * ConditionalOnBean 의 역할
 */
@Retention(RetentionPolicy.RUNTIME)
@MyBooleanCondition(PropertyFiltering.class)
public @interface MyPropertyCondition {
    String target();

    String value();
}
