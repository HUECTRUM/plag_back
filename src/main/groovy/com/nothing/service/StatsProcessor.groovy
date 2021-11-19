package com.nothing.service

import com.nothing.annotations.springcomponents.InjectableService

@InjectableService
class StatsProcessor {
    public static final def statFields = ['Average K/D Ratio', 'Average Kills', 'Average K/R Ratio', 'Win Rate %', 'Matches']

    def processSingleMatchStats(Map<String, List<Map<String, ?>>> matchStats) {
        return matchStats.collectEntries { key, value -> [key, processSingleMapStats(value)] }
    }

    def processSingleMapStats(List<Map<String, ?>> mapStats) {
        return statFields.collectEntries { [it, processSingleMapStats(mapStats, it)] }
    }

    def processSingleMapStats(List<Map<String, ?>> mapStats, String field) {
        def totalMatches = mapStats.sum { it.stats.Matches as double }

        return field != 'Matches'
                ? mapStats.sum { (it.stats[field] as double) * (it.stats.Matches as double) } / totalMatches
                : totalMatches
    }
}
