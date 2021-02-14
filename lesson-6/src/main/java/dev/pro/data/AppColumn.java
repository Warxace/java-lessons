package dev.pro.data;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface AppColumn {
    String name() default "";
    String dbType() default "";
    boolean isPrimaryKey() default false;
}
