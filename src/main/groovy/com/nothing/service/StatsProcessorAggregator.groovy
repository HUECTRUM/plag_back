package com.nothing.service

import com.nothing.annotations.springcomponents.InjectableService

@InjectableService
class StatsProcessorAggregator {
    public final StatsCollector statsCollector
    public final StatsProcessor statsProcessor

    def processStats(List t1Info, List t2Info) {
        return statsCollector.collectStatsByMap(t1Info, t2Info)
                .collect { statsProcessor.processSingleMatchStats(it) }
    }
}
