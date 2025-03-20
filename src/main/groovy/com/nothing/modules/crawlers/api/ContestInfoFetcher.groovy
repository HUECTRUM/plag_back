package com.nothing.modules.crawlers.api

import com.nothing.modules.crawlers.api.data.ContestMetadata

interface ContestInfoFetcher {
    String latestName()

    ContestMetadata fetchInfo(String id)
}