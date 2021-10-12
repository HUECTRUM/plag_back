package com.nothing.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class MatchService {
    private final WebClient client;

    public Mono<String> getMatches() {
        return client
                .get()
                .uri("matches/1-a591aa37-b5a1-4683-bc61-d353a7a20453")
                .retrieve()
                .bodyToMono(String.class);
    }
}
