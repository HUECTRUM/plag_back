package com.nothing.listeners

import com.nothing.annotations.springcomponents.InjectableComponent
import com.nothing.service.StatsProcessor
import com.nothing.service.response.MatchResponseService
import org.javacord.api.entity.message.MessageBuilder
import org.javacord.api.entity.message.embed.EmbedBuilder
import org.javacord.api.event.message.MessageCreateEvent

import static com.nothing.utils.ColorUtils.generateRandomColor

@InjectableComponent
class MapStatsListener extends KeywordListener {
    private static final def mapPool =
            ['de_dust2', 'de_mirage', 'de_train', 'de_ancient', 'de_overpass', 'de_nuke', 'de_vertigo', 'de_inferno']
    private static final def shortenedStatNames = [
            'Average K/D Ratio': 'K/D',
            'Average Kills': 'Kills',
            'Average K/R Ratio': 'K/R',
            'Win Rate %': 'Win%',
            'Matches': 'Matches'
    ]

    public final MatchResponseService matchResponseService

    @Override
    def process(MessageCreateEvent event, List<String> params) {
        def response = matchResponseService.getMatchResponse(params[0])

        def team1Name = response.matchData.teams.faction1.name
        def team2Name = response.matchData.teams.faction2.name

        mapPool.each { map ->
            String msg = StatsProcessor.statFields.inject("") { res, stat ->
                res + getStatStr(stat, team1Name, team2Name, response.team1Stats[map][stat], response.team2Stats[map][stat])
            }

            new MessageBuilder().setEmbed(new EmbedBuilder()
                    .setTitle("${map} stats for match ${params[0][-4..-1]}")
                    .setDescription(msg)
                    .setColor(generateRandomColor())
            ).send(event.channel)
        }
    }

    def getStatStr(String stat, String t1Name, String t2Name, double t1Stat, double t2Stat) {
        def t1Str = "$t1Name - ${t1Stat.round(2)}"
        def t2Str = "$t2Name - ${t2Stat.round(2)}"

        def statDiff = t1Stat > t2Stat ? "${t1Str.discordBoldItalic()}/$t2Str" : "$t1Str/${t2Str.discordBoldItalic()}"

        return "${shortenedStatNames[stat]}: $statDiff" + '\r\n'
    }

    @Override
    def commandName() {
        return '.mapstats'
    }
}
