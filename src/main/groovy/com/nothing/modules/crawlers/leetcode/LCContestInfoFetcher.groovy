package com.nothing.modules.crawlers.leetcode

import com.fasterxml.jackson.databind.ObjectMapper
import com.nothing.helper.annotations.springcomponents.InjectableService
import com.nothing.modules.crawlers.api.ContestInfoFetcher
import com.nothing.modules.crawlers.api.ContestMetadata
import com.nothing.modules.crawlers.api.ProblemMetadata
import org.springframework.web.reactive.function.client.WebClient

@InjectableService class LCContestInfoFetcher implements ContestInfoFetcher {
    private static final def mapper = new ObjectMapper()
    private static def path = 'D:\\scrapper\\LC\\contest-info'

    public final WebClient lcHttpClient

    @Override boolean newContest() { false }

    @Override ContestMetadata fetchLatest() {
        def latestName = latestName()
        parseResponse(latestName, requestContestInfo(latestName))
    }

    @Override ContestMetadata fetchExisting() { mapper.readValue(new File(path).text, ContestMetadata) }


    ContestMetadata parseResponse(id, response) {
        def problems = response.questions.collect {
            [id: it.question_id, name: it.title, contestId: id, key: it.question_id] as ProblemMetadata
        }

        [id: id, name: id, problems: problems] as ContestMetadata
    }

    def requestContestInfo(String id) {
        lcHttpClient.blockingGetWHeader("info/${id}/", 'referer', "https://leetcode.com/contest/${id}/ranking/")
    }

    String latestName() { return 'weekly-contest-441' }
}
