package com.nothing.listeners

import com.nothing.annotations.springcomponents.InjectableComponent
import org.javacord.api.event.message.MessageCreateEvent

@InjectableComponent
class HelpListener extends KeywordListener {
    private static final def helpMsg = """
    ```Available commands:
     .maxlvl <player_name> - max level the player has reached
     .mapstats <room_id> - team stats for a matchmaking lobby
    ```
    """

    @Override
    def process(MessageCreateEvent event, List<String> params) {
        event.channel.sendMessage(helpMsg)
    }

    @Override
    def commandName() {
        return '.help'
    }
}
