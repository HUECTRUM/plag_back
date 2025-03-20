package com.nothing.helper.annotations.springcomponents

import com.nothing.allargsconstructor.AllArgsConstructor
import groovy.transform.AnnotationCollector
import groovy.util.logging.Slf4j
import org.springframework.stereotype.Component

@AnnotationCollector([AllArgsConstructor, Component, Slf4j])
@interface InjectableComponent {
}
