package com.example.tilas.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME) // 指定什么时候起作用
@Target(ElementType.METHOD) // 指定注解的作用范围
public @interface Log {
    // 一个自定义的注解
}
