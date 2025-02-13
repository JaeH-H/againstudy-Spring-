package com.spring.basicjpa.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)//메소드에 적용한다고 선언
@Retention(RetentionPolicy.RUNTIME)//RUNTIME에서 실행다고 선언
public @interface TrackTime {
}
