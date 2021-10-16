package com.nothing.configuration.properties

import com.nothing.annotations.ConfigProperty

@ConfigProperty(value = 'connection', sourceFile = 'classpath:connection.properties')
class ConnectionProperties {
    String url
    String key
}
