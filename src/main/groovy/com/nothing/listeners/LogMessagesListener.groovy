package com.nothing.listeners

import com.nothing.annotations.springcomponents.InjectableComponent
import groovy.util.logging.Slf4j
import org.javacord.api.event.message.MessageCreateEvent
import org.javacord.api.listener.message.MessageCreateListener

@InjectableComponent
@Slf4j
class LogMessagesListener implements MessageCreateListener {
    @Override
    void onMessageCreate(MessageCreateEvent event) {
        log.info('Message received {}', event.messageContent)
    }
}
