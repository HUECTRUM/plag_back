package com.nothing.annotations.springcomponents

import com.nothing.allargsconstructor.AllArgsConstructor
import groovy.transform.AnnotationCollector
import org.springframework.web.bind.annotation.RestController

@AnnotationCollector([AllArgsConstructor, RestController])
@interface InjectableController {
}
