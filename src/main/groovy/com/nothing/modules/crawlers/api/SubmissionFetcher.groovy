package com.nothing.modules.crawlers.api

interface SubmissionFetcher {
    String fetchCode(String cId, String probId, String subId, List<String> info)
}