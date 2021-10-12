package com.nothing.configuration;

import com.nothing.configuration.properties.ConnectionProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

import static java.lang.String.format;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class WebClientConfig {
    public final ConnectionProperties connectionProperties;

    @Bean
    public WebClient dataClient() {
        return WebClient.builder()
                .baseUrl(connectionProperties.url)
                .defaultHeader("accept", "application/json")
                .defaultHeader("Authorization", format("Bearer %s", connectionProperties.key))
                .build();

    }
}
