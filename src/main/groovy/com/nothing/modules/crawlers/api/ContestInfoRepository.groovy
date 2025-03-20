package com.nothing.modules.crawlers.api

interface ContestInfoRepository {
    String getLastId()
    ContestMetadata getById(String id)
    void save(ContestMetadata data)
}
