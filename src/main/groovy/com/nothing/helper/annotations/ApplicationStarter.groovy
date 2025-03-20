package com.nothing.helper.annotations


import groovy.transform.AnnotationCollector
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties

@AnnotationCollector([SpringBootApplication, EnableConfigurationProperties])
@interface ApplicationStarter {
}
