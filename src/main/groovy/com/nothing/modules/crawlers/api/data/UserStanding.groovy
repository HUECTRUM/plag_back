package com.nothing.modules.crawlers.api.data

import groovy.transform.Canonical

@Canonical class UserStanding {
    String id, name
    int rank
    List<Submission> submissions
}
