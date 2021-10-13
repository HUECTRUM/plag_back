package com.nothing.service

import com.nothing.annotations.springcomponents.InjectableService
import com.nothing.http.FaceitDataApi

@InjectableService
class MatchHttpFetcher {
    public final FaceitDataApi faceitDataApi

    def fullApiInfo(String matchId) {
        def matchData = faceitDataApi.getMatch(matchId) as Map<String, ?>

        def (team1Data, team2Data) = ["faction1", "faction2"].collect {
            String fName -> getPlayerIds(matchData, fName)
        }.collect {
            List<String> playerIds -> faceitDataApi.getTeamData(playerIds)
        }

        return [matchData, team1Data, team2Data]
    }

    List<String> getPlayerIds(Map<String, ?> matchData, String factionName) {
        return matchData.teams[factionName].roster.collect { pInfo -> pInfo['player_id'] } as List<String>
    }
}
