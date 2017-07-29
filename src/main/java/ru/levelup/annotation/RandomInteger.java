package ru.levelup.annotation;

import java.lang.annotation.*;

@Target (ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)

public @interface RandomInteger {

    int min() default 10;

    int max() default 100;

}
