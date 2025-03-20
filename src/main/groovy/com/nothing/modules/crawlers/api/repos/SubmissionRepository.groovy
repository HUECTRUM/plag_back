package com.nothing.modules.crawlers.api.repos

interface SubmissionRepository {
    boolean exists(String cId, String probId, String subId)
    void save(String cId, String probId, String subId, String sourceCode)
}