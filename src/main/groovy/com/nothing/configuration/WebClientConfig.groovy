package com.nothing.configuration

import com.nothing.annotations.springcomponents.InjectableConfiguration
import com.nothing.configuration.properties.ConnectionProperties
import org.springframework.context.annotation.Bean
import org.springframework.web.reactive.function.client.ExchangeStrategies
import org.springframework.web.reactive.function.client.WebClient

import static java.lang.String.format

@InjectableConfiguration
class WebClientConfig {
    public final ConnectionProperties connectionProperties

    @Bean
    WebClient dataClient() {
        return WebClient.builder()
                .exchangeStrategies(ExchangeStrategies.builder()
                        .codecs(configurer -> configurer
                                .defaultCodecs()
                                .maxInMemorySize(16 * 1024 * 1024))
                        .build()
                ).baseUrl(connectionProperties.url)
                .defaultHeader('accept', 'application/json')
                .defaultHeader('Authorization', format('Bearer %s', connectionProperties.key))
                .build()
    }
}
