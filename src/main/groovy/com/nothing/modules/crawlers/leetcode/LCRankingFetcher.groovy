package com.nothing.modules.crawlers.leetcode

import com.nothing.helper.annotations.springcomponents.InjectableService
import com.nothing.modules.crawlers.api.RankingFetcher
import com.nothing.modules.crawlers.api.data.Submission
import com.nothing.modules.crawlers.api.data.UserStanding
import org.springframework.web.reactive.function.client.WebClient

@InjectableService class LCRankingFetcher implements RankingFetcher {
    public final WebClient lcHttpClient

    @Override List<UserStanding> fetchNew(String cId) { fetchAll(cId) }

    List<UserStanding> fromPage(String cId, int page) { parseResponse(requestPage(cId, page)) }

    List<UserStanding> parseResponse(response) {
        response.total_rank.collect { userResponse ->
            def uname = userResponse.username
            def userSubmissions = userResponse.submissions.collect { subEntry ->
                [id: subEntry.value.submission_id, probId: subEntry.key,
                 author: uname, language: subEntry.value.lang, additionalInfo:[userResponse.data_region]] as Submission
            }
            [id: uname, name: uname, rank: userResponse.rank as int, submissions: userSubmissions] as UserStanding
        }
    }

    def requestPage(String cId, int page) {
        lcHttpClient.blockingGetWHeader("ranking/$cId/?pagination=$page&region=global_v2",
                'referer', "https://leetcode.com/contest/$cId/ranking/?region=global_v2", 10)
    }

    List<UserStanding> fetchAll(String cid) {
        def result = [], curr = []

        int page = 1
        do {
            log.info("Fetching for page ${page}")
            curr = fromPage(cid, page++)
            result.addAll(curr)
        } while (curr.size() > 0)

        return result
    }
}
