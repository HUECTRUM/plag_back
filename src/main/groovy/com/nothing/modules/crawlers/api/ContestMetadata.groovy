package com.nothing.modules.crawlers.api

import groovy.transform.Canonical

@Canonical class ContestMetadata {
    String id, name
    List<ProblemMetadata> problems
}
