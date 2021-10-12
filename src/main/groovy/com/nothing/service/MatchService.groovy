package com.nothing.service

import com.nothing.annotations.springcomponents.InjectableService
import org.springframework.web.reactive.function.client.WebClient

@InjectableService
class MatchService {
    public final WebClient client

    def getMatches() {
        return client
                .get()
                .uri("matches/1-a591aa37-b5a1-4683-bc61-d353a7a20453")
                .retrieve()
                .bodyToMono(String.class)
    }
}
