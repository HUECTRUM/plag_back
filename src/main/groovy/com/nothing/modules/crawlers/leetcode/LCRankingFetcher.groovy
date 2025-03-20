package com.nothing.modules.crawlers.leetcode

import com.nothing.helper.annotations.springcomponents.InjectableService
import com.nothing.modules.crawlers.api.RankingFetcher
import com.nothing.modules.crawlers.api.data.UserStanding

@InjectableService class LCRankingFetcher implements RankingFetcher {
    @Override List<UserStanding> fetchNew(String cId) {
        []
    }
}
