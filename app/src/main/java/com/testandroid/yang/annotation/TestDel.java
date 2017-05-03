package com.testandroid.yang.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * TestDel
 * Created by yangjiajia on 2017/5/2 0002.
 */
@Documented
@Target(METHOD)
@Retention(RUNTIME)
public @interface TestDel {
    String value() default "Testdddd";

    String vvvv();
}
