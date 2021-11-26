package com.nothing.http

import com.nothing.annotations.springcomponents.InjectableService
import com.nothing.configuration.properties.SteamProperties
import org.springframework.web.reactive.function.client.WebClient

@InjectableService
class SteamApi {
    public final Map<String, WebClient> clients
    public final SteamProperties steamProperties

    def getId64(String vanityId) {
        return clients.steamClient.executeBlockingGet("ResolveVanityURL/v0001?key=${steamProperties.key}&vanityurl=$vanityId")
    }
}
