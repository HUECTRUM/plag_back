package com.nothing.modules.crawlers.leetcode.repos

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import com.nothing.helper.annotations.springcomponents.InjectableService
import com.nothing.modules.crawlers.api.repos.RankingRepository
import com.nothing.modules.crawlers.api.data.UserStanding
import org.springframework.beans.factory.annotation.Value

import static groovy.json.JsonOutput.toJson

@InjectableService class LCRankingRepository implements RankingRepository {
    private static final def mapper = new ObjectMapper()

    @Value("${contestRepo.path}") String rankingPath

    private static def path = { id -> "$rankingPath-$id" }

    @Override
    List<UserStanding> getByContestId(String id) {
        def file = new File(path(id))
        file.exists() ? mapper.readValue(file.text, new TypeReference<List<UserStanding>>(){}) : null
    }

    @Override void save(String cId, List<UserStanding> data) {
        new File(path(cId)).newWriter().withWriter { it << toJson(data) }
    }
}
