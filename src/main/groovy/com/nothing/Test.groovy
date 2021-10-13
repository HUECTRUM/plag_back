package com.nothing

import com.nothing.annotations.springcomponents.InjectableComponent
import com.nothing.service.StatsCollector
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.event.EventListener

@InjectableComponent
class Test {
    public final StatsCollector statsCollectorService

    @EventListener(ApplicationReadyEvent.class)
    void go() {
        def data = statsCollectorService.collectStats "1-b59fde3d-fb48-43c0-9430-eaa58bc2027d"
        println data
    }
}
