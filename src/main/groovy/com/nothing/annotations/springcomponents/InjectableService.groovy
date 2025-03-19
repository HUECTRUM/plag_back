package com.nothing.annotations.springcomponents

import com.nothing.allargsconstructor.AllArgsConstructor
import groovy.transform.AnnotationCollector
import groovy.util.logging.Slf4j
import org.springframework.stereotype.Service

@AnnotationCollector([AllArgsConstructor, Service, Slf4j])
@interface InjectableService {
}
