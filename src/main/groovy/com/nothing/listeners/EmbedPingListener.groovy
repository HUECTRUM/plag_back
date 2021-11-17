package com.nothing.listeners

import com.nothing.annotations.springcomponents.InjectableComponent
import org.javacord.api.entity.message.embed.EmbedBuilder
import org.javacord.api.event.message.MessageCreateEvent

import static com.nothing.utils.ColorUtils.generateRandomColor
import static com.nothing.utils.ResourceUtils.getResourceFile

@InjectableComponent
class EmbedPingListener extends KeywordListener {
    @Override
    def process(MessageCreateEvent event, List<String> params) {
        event.channel.sendMessage(new EmbedBuilder()
                .setAuthor(event.messageAuthor)
                .setTitle('Embed ping response')
                .setDescription('Embed pong')
                .setThumbnail(getResourceFile("pics/faceit3.png"))
                .setColor(generateRandomColor())
        )
    }

    @Override
    def commandName() {
        return '.embedping'
    }
}
