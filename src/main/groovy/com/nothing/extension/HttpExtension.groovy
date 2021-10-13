package com.nothing.extension

import groovy.json.JsonSlurper
import org.springframework.web.reactive.function.client.WebClient

class HttpExtension {
    private static final JsonSlurper internalSlurper = new JsonSlurper()

    static def executeBlockingGet(final WebClient self, final String url) {
        return self
                .get()
                .uri(url)
                .retrieve()
                .bodyToMono(String.class)
                .map(internalSlurper::parseText)
                .block()
    }
}
