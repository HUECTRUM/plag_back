package com.nothing

import com.nothing.annotations.springcomponents.InjectableComponent
import com.nothing.http.FaceitDataApi
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.event.EventListener

@InjectableComponent
class Test {
    public final FaceitDataApi matchService

    @EventListener(ApplicationReadyEvent.class)
    void go() {
        def (generalData, stats) = matchService.getPlayerData("a785840b-c4bc-4d44-b6d9-12ba133a6219")
        println generalData
        println stats
    }
}
