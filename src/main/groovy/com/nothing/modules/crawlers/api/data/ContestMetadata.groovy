package com.nothing.modules.crawlers.api.data

import groovy.transform.Canonical

@Canonical class ContestMetadata {
    String id, name
    List<ProblemMetadata> problems
}
