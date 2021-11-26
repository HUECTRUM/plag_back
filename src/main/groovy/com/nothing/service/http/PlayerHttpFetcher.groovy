package com.nothing.service.http

import com.nothing.annotations.springcomponents.InjectableService
import com.nothing.http.FaceitDataApi

@InjectableService
class PlayerHttpFetcher {
    public final FaceitDataApi dataApi

    def getPlayerMatches(String name) {
        String playerId = dataApi.getPlayerByName(name).player_id
        return [playerId, dataApi.getPlayerHistory(playerId)]
    }
}
