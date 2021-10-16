package com.nothing.controller

import com.nothing.annotations.springcomponents.InjectableController
import com.nothing.service.response.MatchResponseService
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@CrossOrigin(origins = '*', allowedHeaders = '*')
@InjectableController
class StatsController {
    public final MatchResponseService matchResponseService

    @GetMapping('/stats/{matchId}')
    def getMatchStats(@PathVariable String matchId) {
        return matchResponseService.getMatchResponse(matchId)
    }
}
