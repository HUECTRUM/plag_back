package com.nothing.service

import com.nothing.annotations.springcomponents.InjectableService

@InjectableService
class StatsCollector {
    public final MatchHttpFetcher matchHttpFetcher

    def collectStatsByMap(List t1Info, List t2Info) {
        def statsResponse = [t1Info, t2Info].collect { List<?> info -> info[1] }

        return statsResponse
                .collect { getSegments(it) }
                .collect { it.groupBy({ Map<String, ?> segment -> segment.label }) }
    }

    def getSegments(List<Map<String, ?>> teamStats) {
        return teamStats
                .collect { it.segments }
                .flattenOnce()
                .findAll { it.type == 'Map' && it.mode == '5v5' }
    }
}
