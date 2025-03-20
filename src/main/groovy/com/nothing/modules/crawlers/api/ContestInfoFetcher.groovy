package com.nothing.modules.crawlers.api

interface ContestInfoFetcher {
    boolean newContest()
    ContestMetadata fetchLatest()
    ContestMetadata fetchExisting()

    default ContestMetadata getLastContest() { newContest() ? fetchLatest() : fetchExisting() }
}