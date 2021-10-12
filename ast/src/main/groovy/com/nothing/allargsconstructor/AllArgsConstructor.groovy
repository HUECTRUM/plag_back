package com.nothing.allargsconstructor

import org.codehaus.groovy.transform.GroovyASTTransformationClass

import java.lang.annotation.Retention
import java.lang.annotation.Target

import static java.lang.annotation.ElementType.TYPE
import static java.lang.annotation.RetentionPolicy.SOURCE

@Retention(SOURCE)
@Target(TYPE)
@GroovyASTTransformationClass(classes = AllArgsConstructorASTTransformation)
@interface AllArgsConstructor {
}
