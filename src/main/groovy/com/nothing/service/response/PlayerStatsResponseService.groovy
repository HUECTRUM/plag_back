package com.nothing.service.response

import com.nothing.annotations.springcomponents.InjectableService
import com.nothing.service.http.PlayerHttpFetcher

@InjectableService
class PlayerStatsResponseService {
    public final PlayerHttpFetcher playerHttpFetcher

    def getPlayerMaxlvl(String playerName) {
        def (playerId, matches) = playerHttpFetcher.getPlayerMatches(playerName)
        return matches.collect{getMatchLevel(it, playerId)}.max()
    }

    def getMatchLevel(Map match, String playerId) {
        return (match.teams.faction1.players + match.teams.faction2.players)
                .find {it.player_id == playerId}
                ?.skill_level ?: 0
    }
}
