package com.nothing.configuration

import com.nothing.annotations.springcomponents.InjectableConfiguration
import org.javacord.api.DiscordApi
import org.javacord.api.DiscordApiBuilder
import org.javacord.api.listener.message.MessageCreateListener
import org.springframework.context.annotation.Bean

import static java.lang.System.getenv

@InjectableConfiguration
class DiscordConfig {
    public final List<MessageCreateListener> listeners

    @Bean
    DiscordApi discordApi() {
        DiscordApiBuilder result = new DiscordApiBuilder()
                .setToken(getenv("token"))
                .setWaitForServersOnStartup(false)

        listeners.each { result.addMessageCreateListener(it) }

        return result.login().join()
    }
}
