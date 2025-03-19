package com.nothing.annotations.springcomponents

import com.nothing.allargsconstructor.AllArgsConstructor
import groovy.transform.AnnotationCollector
import groovy.util.logging.Slf4j
import org.springframework.context.annotation.Configuration

@AnnotationCollector([AllArgsConstructor, Configuration, Slf4j])
@interface InjectableConfiguration {
}
