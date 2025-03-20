package com.nothing.modules.crawlers.api.data

import groovy.transform.Canonical

@Canonical class ProblemMetadata {
    String contestId, id, name, key
    int acCount = 0, attempted = 0
}
