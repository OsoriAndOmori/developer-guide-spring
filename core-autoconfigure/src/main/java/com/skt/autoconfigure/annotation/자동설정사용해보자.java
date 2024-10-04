package com.skt.autoconfigure.annotation;

import com.skt.autoconfigure.AutoImportSelector;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME) // 이거 넣어줘야 실행시점에 인식 가능. 기본값은 CLASS
public @interface 자동설정사용해보자 {
    Class<AutoImportSelector> value();
}
