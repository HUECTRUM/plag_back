package com.nothing.modules.crawlers.leetcode

import com.fasterxml.jackson.databind.ObjectMapper
import com.nothing.helper.annotations.springcomponents.InjectableService
import com.nothing.modules.crawlers.api.ContestInfoRepository
import com.nothing.modules.crawlers.api.ContestMetadata
import groovy.json.JsonOutput

@InjectableService class LCContestInfoRepository implements ContestInfoRepository {
    private static final def mapper = new ObjectMapper()
    private static def path = 'D:\\scrapper\\LC\\contest-info'

    @Override String getLastId() { return 'weekly-contest-441' }

    @Override ContestMetadata getById(String id) {
        def file = new File(path)
        file.exists() ? mapper.readValue(file.text, ContestMetadata) : null
    }

    @Override void save(ContestMetadata data) { new File(path).write(JsonOutput.toJson(data)) }
}
