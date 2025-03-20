package com.nothing.modules.crawlers.api

import com.nothing.modules.crawlers.api.data.ContestMetadata

interface ContestInfoRepository {
    ContestMetadata getById(String id)
    void save(ContestMetadata data)
}
