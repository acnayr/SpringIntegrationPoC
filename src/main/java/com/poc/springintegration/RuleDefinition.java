package com.poc.springintegration;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface RuleDefinition {

    public int priority() default 99;
    public String name() default "";
    public boolean disabled() default false;
    public boolean finalized() default false;


}
