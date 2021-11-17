package com.nothing.listeners

import com.nothing.annotations.springcomponents.InjectableComponent
import org.javacord.api.event.message.MessageCreateEvent

@InjectableComponent
class PingListener extends KeywordListener {
    @Override
    def process(MessageCreateEvent event, List<String> params) {
        event.channel.sendMessage('.pong')
    }

    @Override
    def commandName() {
        return '.ping'
    }
}
