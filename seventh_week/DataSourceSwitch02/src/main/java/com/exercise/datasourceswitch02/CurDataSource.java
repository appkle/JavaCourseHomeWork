package com.exercise.datasourceswitch02;

import java.lang.annotation.*;

//指定要使用的数据源

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CurDataSource {
    String name() default "";
}
