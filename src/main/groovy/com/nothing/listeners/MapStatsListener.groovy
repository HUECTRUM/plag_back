package com.nothing.listeners

import com.nothing.annotations.springcomponents.InjectableComponent
import com.nothing.service.StatsProcessor
import com.nothing.service.response.MatchResponseService
import com.nothing.utils.ResourceUtils
import org.javacord.api.entity.message.MessageBuilder
import org.javacord.api.entity.message.embed.EmbedBuilder
import org.javacord.api.event.message.MessageCreateEvent
import org.javacord.api.listener.message.MessageCreateListener

import static com.nothing.utils.ColorUtils.generateRandomColor

@InjectableComponent
class MapStatsListener implements MessageCreateListener {
    private static final def mapPool =
            ['de_dust2', 'de_mirage', 'de_train', 'de_ancient', 'de_overpass', 'de_nuke', 'de_vertigo', 'de_inferno']

    public final MatchResponseService matchResponseService

    @Override
    void onMessageCreate(MessageCreateEvent event) {
        def msgWords = event.messageContent.split("\\s+")
        if (!msgWords[0].equalsIgnoreCase(".mapstats")) {
            return
        }

        def response = matchResponseService.getMatchResponse(msgWords[1])

        def team1Name = response.matchData.teams.faction1.name
        def team2Name = response.matchData.teams.faction2.name

        mapPool.each { map ->
            String msg = StatsProcessor.statFields.inject("") { res, stat ->
                def team1Stat = response.team1Stats[map][stat]
                def team2Stat = response.team2Stats[map][stat]

                if (team1Stat > team2Stat) {
                    res += "$stat: __**$team1Name - $team1Stat**__/$team2Name - $team2Stat"
                } else {
                    res += "$stat: $team1Name - $team1Stat/__**$team2Name - $team2Stat**__"
                }
                res += '\r\n\r\n'
                return res
            }

            new MessageBuilder().setEmbed(new EmbedBuilder()
                    .setAuthor(event.messageAuthor)
                    .setTitle("Map ${map} stats for match ${msgWords[1]}")
                    .setDescription(msg)
                    .setThumbnail(ResourceUtils.getResourceFile("pics/map_icon_${map}.png"))
                    .setColor(generateRandomColor())
            ).send(event.channel)
        }
    }
}
