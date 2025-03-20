package com.nothing.modules.crawlers.api

interface RankingFetcher {
    boolean needUpdate()
    List<UserStanding> fetchNew()
    List<UserStanding> fetchExisting()

    default ContestMetadata getLastContest() { needUpdate() ? fetchNew() : fetchExisting() }
}