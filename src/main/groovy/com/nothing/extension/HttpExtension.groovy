package com.nothing.extension

import groovy.json.JsonSlurper
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.WebClientResponseException

class HttpExtension {
    private static final JsonSlurper internalSlurper = new JsonSlurper()

    static def blockingGet(final WebClient self, final String url) {
        return self
                .get()
                .uri(url)
                .retrieve()
                .bodyToMono(String.class)
                .map(internalSlurper::parseText)
                .block()
    }

    static def blockingGetWHeader(final WebClient self, final String url, String name, String value) {
        return self
                .get()
                .headers(httpHeaders -> {
                    httpHeaders.set(name, value)
                })
                .uri(url)
                .retrieve()
                .bodyToMono(String.class)
                .map(internalSlurper::parseText)
                .block()
    }
}
