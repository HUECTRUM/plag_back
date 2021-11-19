package com.nothing.listeners

import com.nothing.annotations.springcomponents.InjectableComponent
import com.nothing.service.PlayerStatsCollector
import groovy.util.logging.Slf4j
import org.javacord.api.entity.message.MessageBuilder
import org.javacord.api.entity.message.embed.EmbedBuilder
import org.javacord.api.event.message.MessageCreateEvent

import static com.nothing.utils.ColorUtils.generateRandomColor

@InjectableComponent
@Slf4j
class MaxLevelListener extends KeywordListener {
    public PlayerStatsCollector playerStatsCollector

    @Override
    def process(MessageCreateEvent event, List<String> params) {
        def level = playerStatsCollector.getPlayerMaxlvl(params[0])

        new MessageBuilder().setEmbed(new EmbedBuilder()
                .setAuthor(event.messageAuthor)
                .setTitle('Max faceit level')
                .setDescription("Player ${params[0]} has reached lvl $level")
                .setColor(generateRandomColor())
        ).send(event.channel)
    }

    @Override
    def commandName() {
        return '.maxlvl'
    }
}
