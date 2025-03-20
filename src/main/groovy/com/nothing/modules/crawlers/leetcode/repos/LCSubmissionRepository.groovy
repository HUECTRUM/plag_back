package com.nothing.modules.crawlers.leetcode.repos


import com.nothing.helper.annotations.springcomponents.InjectableService
import com.nothing.modules.crawlers.api.repos.SubmissionRepository

@InjectableService class LCSubmissionRepository implements SubmissionRepository {
    private static def path = { cId, probId, subId -> "D:\\scrapper\\LC\\$cId\\$probId\\${subId}.txt" }

    @Override boolean exists(String cId, String probId, String subId) { new File(path(cId, probId, subId)).exists() }

    @Override void save(String cId, String probId, String subId, String sourceCode) {
        new File(path(cId, probId, subId)).newWriter().withWriter { it << sourceCode }
    }
}
