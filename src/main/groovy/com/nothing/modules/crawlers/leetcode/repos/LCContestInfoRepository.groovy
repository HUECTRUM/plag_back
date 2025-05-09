package com.nothing.modules.crawlers.leetcode.repos

import com.fasterxml.jackson.databind.ObjectMapper
import com.nothing.helper.annotations.springcomponents.InjectableService
import com.nothing.modules.crawlers.api.repos.ContestInfoRepository
import com.nothing.modules.crawlers.api.data.ContestMetadata
import org.springframework.beans.factory.annotation.Value

import static groovy.json.JsonOutput.toJson

@InjectableService class LCContestInfoRepository implements ContestInfoRepository {
    private static final def mapper = new ObjectMapper()

    @Value("${contestRepo.path}") String path

    @Override ContestMetadata getById(String id) {
        def file = new File(path)
        file.exists() ? mapper.readValue(file.text, ContestMetadata) : null
    }

    @Override void save(ContestMetadata data) { new File(path).newWriter().withWriter { it << toJson(data) } }
}
