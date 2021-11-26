package com.nothing.configuration

import com.nothing.annotations.springcomponents.InjectableConfiguration
import com.nothing.configuration.properties.ConnectionProperties
import com.nothing.configuration.properties.SteamProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Primary
import org.springframework.web.reactive.function.client.WebClient

import static com.nothing.utils.WebClientUtils.buildWebClient
import static java.lang.String.format

@InjectableConfiguration
class WebClientConfig {
    public final ConnectionProperties connectionProperties
    public final SteamProperties steamProperties

    @Bean
    @Primary
    WebClient dataClient() {
        return buildWebClient(connectionProperties.url, [
                accept       : 'application/json',
                Authorization: format('Bearer %s', connectionProperties.key)
        ])
    }

    @Bean
    WebClient v1Client() {
        return buildWebClient(connectionProperties.oldUrl, [:])
    }

    @Bean
    WebClient steamClient() {
        return buildWebClient(steamProperties.apiurl, [:])
    }
}
