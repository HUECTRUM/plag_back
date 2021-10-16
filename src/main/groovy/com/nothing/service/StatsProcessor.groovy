package com.nothing.service

import com.nothing.annotations.springcomponents.InjectableService

@InjectableService
class StatsProcessor {
    private static final def statFields = ['Average K/D Ratio', 'Average Kills', 'Average K/R Ratio', 'Win Rate %']

    def processSingleMatchStats(Map<String, List<Map<String, ?>>> matchStats) {
        return matchStats.collectEntries { key, value -> [key, processSingleMapStats(value)] }
    }

    def processSingleMapStats(List<Map<String, ?>> mapStats) {
        return statFields.collectEntries { [it, processSingleMapStats(mapStats, it)] }
    }

    def processSingleMapStats(List<Map<String, ?>> mapStats, String field) {
        return mapStats.sum { (it.stats[field] as double) * (it.stats.Matches as double) } /
                mapStats.sum { it.stats.Matches as double }
    }
}
