package com.nothing.annotations.springcomponents

import com.nothing.allargsconstructor.AllArgsConstructor
import groovy.transform.AnnotationCollector
import groovy.util.logging.Slf4j
import org.springframework.web.bind.annotation.RestController

@AnnotationCollector([AllArgsConstructor, RestController, Slf4j])
@interface InjectableController {
}
