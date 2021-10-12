package com.nothing.annotations.springcomponents

import com.nothing.allargsconstructor.AllArgsConstructor
import groovy.transform.AnnotationCollector
import org.springframework.context.annotation.Configuration

@AnnotationCollector([AllArgsConstructor, Configuration])
@interface InjectableConfiguration {
}
