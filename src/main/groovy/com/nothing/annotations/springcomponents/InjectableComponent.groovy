package com.nothing.annotations.springcomponents

import com.nothing.allargsconstructor.AllArgsConstructor
import groovy.transform.AnnotationCollector
import org.springframework.stereotype.Component

@AnnotationCollector([AllArgsConstructor, Component])
@interface InjectableComponent {
}
