package com.skt.autoconfigure.annotation;

import com.skt.autoconfigure.match.FilteringMyCondition;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * ConditionalOnClass
 * ConditionalOnBean 의 역할
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface MyBooleanCondition {
    Class<? extends FilteringMyCondition> value();
}
