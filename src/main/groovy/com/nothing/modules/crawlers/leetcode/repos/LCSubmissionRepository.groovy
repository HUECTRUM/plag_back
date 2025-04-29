package com.nothing.modules.crawlers.leetcode.repos


import com.nothing.helper.annotations.springcomponents.InjectableService
import com.nothing.modules.crawlers.api.repos.SubmissionRepository
import org.springframework.beans.factory.annotation.Value

@InjectableService class LCSubmissionRepository implements SubmissionRepository {
    @Value("${contestRepo.path}") String rankingPath

    private static def path = { cId, probId, subId -> "$rankingPath\\$cId\\$probId\\${subId}.txt" }

    @Override boolean exists(String cId, String probId, String subId) { new File(path(cId, probId, subId)).exists() }

    @Override void save(String cId, String probId, String subId, String sourceCode) {
        new File(path(cId, probId, subId)).newWriter().withWriter { it << sourceCode }
    }
}
