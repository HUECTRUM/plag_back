package com.nothing.configuration.properties

import com.nothing.annotations.ConfigProperty

@ConfigProperty(value = 'steam', sourceFile = 'classpath:steam.properties')
class SteamProperties {
    String apiurl
    String key
}
