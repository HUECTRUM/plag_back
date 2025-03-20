package com.nothing.modules.crawlers.api.data

import groovy.transform.Canonical

@Canonical class Submission {
    String id, probId, author, language, sourceCode
    List<String> additionalInfo
}
