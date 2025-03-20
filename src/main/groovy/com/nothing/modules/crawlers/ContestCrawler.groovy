package com.nothing.modules.crawlers

import com.nothing.helper.annotations.springcomponents.InjectableService
import com.nothing.modules.crawlers.api.ContestInfoFetcher
import com.nothing.modules.crawlers.api.ContestInfoRepository
import com.nothing.modules.crawlers.api.RankingFetcher
import com.nothing.modules.crawlers.api.RankingRepository
import com.nothing.modules.crawlers.api.data.ContestMetadata
import com.nothing.modules.crawlers.api.data.UserStanding

@InjectableService class ContestCrawler {
    public final ContestInfoFetcher contestInfoFetcher
    public final ContestInfoRepository contestInfoRepository
    public final RankingRepository rankingRepository
    public final RankingFetcher rankingFetcher

    void runCrawler() {
        def latestName = contestInfoFetcher.latestName()

        //todo: need?
        ContestMetadata metadata = contestInfoRepository.getById(latestName)
        if (metadata == null) {
            log.info("Fetching new contest info for ${latestName}")

            metadata = contestInfoFetcher.fetchInfo(latestName)
            contestInfoRepository.save(metadata)
        }

        List<UserStanding> standings = rankingRepository.getByContestId(latestName)
        if (standings == null) {
            log.info("Fetching new rankings for ${latestName}")
            standings = rankingFetcher.fetchNew(latestName)
        }
    }
}
