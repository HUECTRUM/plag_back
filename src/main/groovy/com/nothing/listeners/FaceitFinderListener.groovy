package com.nothing.listeners

import com.nothing.annotations.springcomponents.InjectableComponent
import com.nothing.service.response.PlayerFinderResponseService
import org.javacord.api.entity.message.MessageBuilder
import org.javacord.api.entity.message.embed.EmbedBuilder
import org.javacord.api.event.message.MessageCreateEvent

import static com.nothing.utils.ColorUtils.generateRandomColor

@InjectableComponent
class FaceitFinderListener extends KeywordListener {
    public final PlayerFinderResponseService responseService

    @Override
    def process(MessageCreateEvent event, List<String> params) {
        def faceitName = responseService.findPlayername(params[0])
        def link = faceitName ? faceitName.generateFaceitNameLink() : 'player not found'

        new MessageBuilder().setEmbed(new EmbedBuilder()
                .setAuthor(event.messageAuthor)
                .setTitle('Faceit account finder')
                .setDescription("Player ${params[0]} faceit account: $link")
                .setColor(generateRandomColor())
        ).send(event.channel)
    }

    @Override
    def commandName() {
        return '.findfaceit'
    }
}
