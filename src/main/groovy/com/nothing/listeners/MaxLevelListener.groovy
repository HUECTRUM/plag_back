package com.nothing.listeners

import com.nothing.annotations.springcomponents.InjectableComponent
import com.nothing.service.PlayerStatsCollector
import com.nothing.utils.ResourceUtils
import org.javacord.api.entity.message.MessageBuilder
import org.javacord.api.entity.message.embed.EmbedBuilder
import org.javacord.api.event.message.MessageCreateEvent
import org.javacord.api.listener.message.MessageCreateListener

import static com.nothing.utils.ColorUtils.generateRandomColor

@InjectableComponent
class MaxLevelListener implements MessageCreateListener {
    public PlayerStatsCollector playerStatsCollector

    @Override
    void onMessageCreate(MessageCreateEvent event) {
        def msgWords = event.messageContent.split("\\s+")
        if (!msgWords[0].equalsIgnoreCase(".maxlvl")) {
            return
        }

        def level = playerStatsCollector.getPlayerMaxlvl(msgWords[1])

        new MessageBuilder().setEmbed(new EmbedBuilder()
                .setAuthor(event.messageAuthor)
                .setTitle('Max faceit level')
                .setDescription("Player ${msgWords[1]} has reached lvl $level")
                .setThumbnail(ResourceUtils.getResourceFile("pics/faceit${level}.png"))
                .setColor(generateRandomColor())
        ).send(event.channel)
    }
}
