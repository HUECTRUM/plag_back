package com.nothing.service.response

import com.nothing.annotations.springcomponents.InjectableService
import com.nothing.http.FaceitDataApi
import com.nothing.http.SteamApi

@InjectableService
class PlayerFinderResponseService {
    static def steamIdPattern = ~'\\d{10,20}'

    public final FaceitDataApi faceitDataApi
    public final SteamApi steamApi

    def findPlayername(String vanityId) {
        def id64 = steamApi.getId64(vanityId).response.steamid ?:
                (vanityId =~ steamIdPattern) ? vanityId : 'no_vanity_id_replacement_found'

        def apiResp = faceitDataApi.getPlayerBySteamId(id64).payload.players.results
        return apiResp ? apiResp[0].nickname : null
    }
}
