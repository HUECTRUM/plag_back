package com.nothing.annotations.springcomponents

import com.nothing.allargsconstructor.AllArgsConstructor
import groovy.transform.AnnotationCollector
import org.springframework.stereotype.Service

@AnnotationCollector([AllArgsConstructor, Service])
@interface InjectableService {
}
