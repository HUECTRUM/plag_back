package com.nothing

import com.nothing.annotations.springcomponents.InjectableComponent
import com.nothing.http.FaceitDataApi
import com.nothing.service.MatchHttpFetcher
import com.nothing.service.StatsCollectorService
import org.apache.groovy.json.internal.LazyMap
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.event.EventListener

@InjectableComponent
class Test {
    public final StatsCollectorService statsCollectorService

    @EventListener(ApplicationReadyEvent.class)
    void go() {
        def data = statsCollectorService.collectStats "1-b59fde3d-fb48-43c0-9430-eaa58bc2027d"
        println data
    }
}
