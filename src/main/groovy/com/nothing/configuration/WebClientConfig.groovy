package com.nothing.configuration

import com.nothing.annotations.springcomponents.InjectableConfiguration
import com.nothing.configuration.properties.ConnectionProperties
import org.springframework.context.annotation.Bean
import org.springframework.web.reactive.function.client.WebClient

import static java.lang.String.format

@InjectableConfiguration
class WebClientConfig {
    public final ConnectionProperties connectionProperties

    @Bean
    WebClient dataClient() {
        return WebClient.builder()
                .baseUrl(connectionProperties.url)
                .defaultHeader('accept', 'application/json')
                .defaultHeader('Authorization', format('Bearer %s', connectionProperties.key))
                .build()
    }
}
