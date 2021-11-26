package com.nothing.utils

import org.springframework.web.reactive.function.client.ExchangeStrategies
import org.springframework.web.reactive.function.client.WebClient

class WebClientUtils {
    static def buildWebClient(String baseUrl, Map<String, String> headers) {
        def headersBuilder = WebClient.builder()
                .exchangeStrategies(ExchangeStrategies.builder()
                        .codecs(configurer -> configurer
                                .defaultCodecs()
                                .maxInMemorySize(16 * 1024 * 1024))
                        .build()
                ).baseUrl(baseUrl)

        headers.each { headersBuilder = headersBuilder.defaultHeader(it.key, it.value) }

        return headersBuilder.build()
    }
}
