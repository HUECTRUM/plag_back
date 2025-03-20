package com.nothing.extension

import groovy.json.JsonSlurper
import org.slf4j.LoggerFactory
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.WebClientResponseException

import static org.slf4j.LoggerFactory.getLogger

class HttpExtension {
    private static final def expBackoff = [1, 2, 4, 8, 15, 30]

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

    static def blockingGetWHeader(final WebClient self, final String url, String name, String value, int retriesCnt) {
        int retry = 0, retryIdx = 0

        while (retry < retriesCnt) {
            try {
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
            } catch (Exception ignored) {
                getLogger(HttpExtension).warn("Request failed, retry $retry")

                sleep(expBackoff[retryIdx] * 1000)

                retry += 1
                if (retryIdx < expBackoff.size() - 1) retryIdx += 1
            }
            return null
        }
    }
}
