package com.nothing

import com.nothing.annotations.springcomponents.InjectableComponent
import com.nothing.service.MatchService
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.event.EventListener

@InjectableComponent
class Test {
    public final MatchService matchService

    @EventListener(ApplicationReadyEvent.class)
    void go() {
        System.out.println matchService.getMatches().block()
    }
}
