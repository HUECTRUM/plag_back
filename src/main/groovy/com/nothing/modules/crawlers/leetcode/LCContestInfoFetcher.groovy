package com.nothing.modules.crawlers.leetcode


import com.nothing.helper.annotations.springcomponents.InjectableService
import com.nothing.modules.crawlers.api.ContestInfoFetcher
import com.nothing.modules.crawlers.api.data.ContestMetadata
import com.nothing.modules.crawlers.api.data.ProblemMetadata
import org.springframework.web.reactive.function.client.WebClient

@InjectableService class LCContestInfoFetcher implements ContestInfoFetcher {
    public final WebClient lcHttpClient

    @Override ContestMetadata fetchInfo(String id) { parseResponse(id, requestContestInfo(id)) }
    @Override String latestName() { return 'weekly-contest-441' }


    ContestMetadata parseResponse(id, response) {
        def problems = response.questions.collect {
            [id: it.question_id, name: it.title, contestId: id, key: it.question_id] as ProblemMetadata
        }

        [id: id, name: id, problems: problems] as ContestMetadata
    }

    def requestContestInfo(String id) {
        lcHttpClient.blockingGetWHeader("info/${id}/", 'referer', "https://leetcode.com/contest/${id}/ranking/")
    }
}
