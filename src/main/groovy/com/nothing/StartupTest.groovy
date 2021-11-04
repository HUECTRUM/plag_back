package com.nothing

import com.fasterxml.jackson.databind.ObjectMapper
import com.nothing.annotations.springcomponents.InjectableComponent
import com.nothing.http.FaceitDataApi
import com.nothing.service.PlayerHttpFetcher
import com.nothing.service.PlayerStatsCollector
import com.nothing.service.StatsCollector
import com.nothing.service.response.MatchResponseService
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.event.EventListener

@InjectableComponent
class StartupTest {
    public final FaceitDataApi dataApi
    public final MatchResponseService responseService

    @EventListener(ApplicationReadyEvent.class)
    def doThings() {
        def resp = responseService.getMatchResponse('1-19d4d45a-ff29-44ba-baa0-9b2bb241bc36')
        println(resp)
    }
}
