package com.nothing.modules.crawlers

import com.nothing.helper.annotations.springcomponents.InjectableService
import com.nothing.modules.crawlers.api.ContestInfoFetcher
import com.nothing.modules.crawlers.api.ContestInfoRepository
import com.nothing.modules.crawlers.api.ContestMetadata

@InjectableService class ContestCrawler {
    public final ContestInfoFetcher contestInfoFetcher
    public final ContestInfoRepository contestInfoRepository

    void runCrawler() {
        def latestName = contestInfoFetcher.latestName()

        ContestMetadata metadata = contestInfoRepository.getById(latestName)
        if (metadata == null) {
            metadata = contestInfoFetcher.fetchInfo(latestName)
            contestInfoRepository.save(metadata)
        }


    }
}
