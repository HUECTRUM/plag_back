package com.nothing.service

import com.nothing.annotations.springcomponents.InjectableService

@InjectableService
class StatsCollectorService {
    public final MatchHttpFetcher matchHttpFetcher

    def collectStats(String matchId) {
        def (_, t1Info, t2Info) = matchHttpFetcher.fullApiInfo(matchId)
        def statsResponse = [t1Info, t2Info].collect { List<?> info -> info[1] }

        def (t1Segments, t2Segments) = getSegments(statsResponse)
        return [t1Segments, t2Segments]
    }

    def getSegments(List<List<Map>> statsResponse) {
        return statsResponse.collect { teamStats ->
            teamStats.collect {
                Map<String, ?> stats -> stats.segments
            }.flattenOnce().findAll{
                Map<String, ?> segments -> segments.type == 'Map' && segments.mode == '5v5'
            }
        }
    }
}
