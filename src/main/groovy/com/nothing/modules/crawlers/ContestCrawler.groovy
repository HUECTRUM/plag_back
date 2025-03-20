package com.nothing.modules.crawlers

import com.nothing.helper.annotations.springcomponents.InjectableService
import com.nothing.modules.crawlers.api.ContestInfoFetcher

import javax.annotation.PostConstruct

@InjectableService class ContestCrawler {
    public final ContestInfoFetcher contestInfoFetcher

    void runCrawler() {
        def metadata = contestInfoFetcher.getLastContest()
        log.info("Got metadata ${metadata}")
    }

    @PostConstruct void go() { runCrawler() }
}
