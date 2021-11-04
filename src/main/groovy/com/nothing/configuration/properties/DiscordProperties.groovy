package com.nothing.configuration.properties

import com.nothing.annotations.ConfigProperty

@ConfigProperty(value = 'discord', sourceFile = 'classpath:discord.properties')
class DiscordProperties {
    String key
}
