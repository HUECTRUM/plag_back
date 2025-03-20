package com.nothing.modules.crawlers.api

interface ContestInfoFetcher {
    String latestName()
    ContestMetadata fetchInfo(String id)
}