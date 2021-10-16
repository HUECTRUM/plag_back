package com.nothing.annotations

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource
import org.springframework.core.annotation.AliasFor

import java.lang.annotation.Retention
import java.lang.annotation.Target

import static java.lang.annotation.ElementType.TYPE
import static java.lang.annotation.RetentionPolicy.RUNTIME

@Target(TYPE)
@Retention(RUNTIME)
@Configuration
@ConfigurationProperties
@PropertySource('')
@interface ConfigProperty {
    @AliasFor(annotation = ConfigurationProperties, attribute = 'prefix')
    String value() default '';

    @AliasFor(annotation = PropertySource, attribute = 'value')
    String sourceFile() default 'classpath:application.properties';
}
