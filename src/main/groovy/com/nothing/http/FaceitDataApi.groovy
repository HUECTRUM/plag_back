package com.nothing.http

import com.nothing.annotations.springcomponents.InjectableService

import org.springframework.web.reactive.function.client.WebClient

import static java.lang.System.currentTimeMillis

@InjectableService
class FaceitDataApi {
    public final WebClient client

    def getMatch(String matchId) {
        return client.executeBlockingGet("matches/$matchId")
    }

    def getPlayerByName(String playername) {
        return client.executeBlockingGet("/players?nickname=$playername&game=csgo")
    }

    def getPlayerData(String playerId) {
        return client.executeBlockingGet("players/$playerId")
    }

    def getPlayerStats(String playerId) {
        return client.executeBlockingGet("players/$playerId/stats/csgo")
    }

    def getTeamData(List<String> playerIds) {
        return [playerIds.collect { getPlayerData(it) }, playerIds.collect { getPlayerStats(it) }]
    }

    def getPlayerHistory(String playerId) {
        long maxTimestamp = currentTimeMillis() / 1000L

        return [].generateUntilEmpty {
            def batch = client.executeBlockingGet("/players/$playerId/history?game=csgo&from=1325376000&to=$maxTimestamp&limit=100")
                    .items
                    .sort { it.started_at }

            maxTimestamp = batch[0]?.started_at ?: 0
            return batch
        }
    }
}
