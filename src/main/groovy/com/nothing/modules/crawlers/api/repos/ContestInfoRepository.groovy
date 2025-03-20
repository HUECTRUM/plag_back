package com.nothing.modules.crawlers.api.repos

import com.nothing.modules.crawlers.api.data.ContestMetadata

interface ContestInfoRepository {
    ContestMetadata getById(String id)
    void save(ContestMetadata data)
}
