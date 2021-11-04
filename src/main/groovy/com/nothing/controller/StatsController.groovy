package com.nothing.controller

import com.nothing.annotations.springcomponents.InjectableController
import com.nothing.service.PlayerStatsCollector
import com.nothing.service.response.MatchResponseService
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@CrossOrigin(origins = '*', allowedHeaders = '*')
@InjectableController
class StatsController {
    public final MatchResponseService matchResponseService
    public final PlayerStatsCollector playerStatsCollector

    @GetMapping('/stats/{matchId}')
    def getMatchStats(@PathVariable String matchId) {
        return matchResponseService.getMatchResponse(matchId)
    }

    @GetMapping('/stats/{playerName}/highestlvl')
    def getHighestPlayerLevel(@PathVariable String playerName) {
        return playerStatsCollector.getPlayerMaxlvl(playerName)
    }
}
