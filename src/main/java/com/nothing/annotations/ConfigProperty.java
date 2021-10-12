package com.nothing.annotations;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Configuration
@ConfigurationProperties
@PropertySource("")
public @interface ConfigProperty {
    @AliasFor(annotation = ConfigurationProperties.class, attribute = "prefix")
    String value() default "";

    @AliasFor(annotation = PropertySource.class, attribute = "value")
    String sourceFile() default "classpath:application.properties";
}
