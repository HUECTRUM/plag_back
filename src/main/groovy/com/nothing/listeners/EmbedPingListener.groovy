package com.nothing.listeners

import com.nothing.annotations.springcomponents.InjectableComponent
import groovy.util.logging.Slf4j
import org.javacord.api.entity.message.embed.EmbedBuilder
import org.javacord.api.event.message.MessageCreateEvent

import static com.nothing.utils.ColorUtils.generateRandomColor
import static com.nothing.utils.ResourceUtils.getResourceFile

@InjectableComponent
@Slf4j
class EmbedPingListener extends KeywordListener {
    @Override
    def process(MessageCreateEvent event, List<String> params) {
        log.info('Embed ping done')
        def file = getResourceFile('pics/faceit3.png')
        log.info('Resource file found')
        def msgBuilder = new EmbedBuilder()
                .setAuthor(event.messageAuthor)
                .setTitle('Embed ping response')
                .setDescription('Embed pong')
                .setThumbnail(file)
                .setColor(generateRandomColor())
        log.info('Msg builder done')
        event.channel.sendMessage(msgBuilder)
        log.info('Msg sent')
    }

    @Override
    def commandName() {
        return '.embedping'
    }
}
