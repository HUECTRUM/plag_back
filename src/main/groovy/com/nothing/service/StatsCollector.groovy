package com.nothing.service

import com.nothing.annotations.springcomponents.InjectableService

@InjectableService
class StatsCollector {
    public final MatchHttpFetcher matchHttpFetcher

    def collectStatsByMap(String matchId) {
        def (_, t1Info, t2Info) = matchHttpFetcher.fullApiInfo(matchId)
        def statsResponse = [t1Info, t2Info].collect { List<?> info -> info[1] }

        def groupedByMap = statsResponse.collect {
            teamStats -> getSegments(teamStats)
        }.collect {
            teamSegment -> teamSegment.groupBy({ Map<String, ?> segment -> segment.label })
        }
        return groupedByMap
    }

    def getSegments(List<Map> teamStats) {
        return teamStats.collect {
            Map<String, ?> stats -> stats.segments
        }.flattenOnce().findAll {
            Map<String, ?> segments -> segments.type == 'Map' && segments.mode == '5v5'
        }
    }
}
