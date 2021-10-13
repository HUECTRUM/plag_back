package com.nothing.http

import com.nothing.annotations.springcomponents.InjectableService
import org.springframework.web.reactive.function.client.WebClient

@InjectableService
class FaceitDataApi {
    public final WebClient client

    def getMatch(String matchId) {
        return client.executeBlockingGet("matches/$matchId")
    }

    def getPlayerData(String playerId) {
        return [
                client.executeBlockingGet("players/$playerId"),
                client.executeBlockingGet("players/$playerId/stats/csgo")
        ]
    }
}
