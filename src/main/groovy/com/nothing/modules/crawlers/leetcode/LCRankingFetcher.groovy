package com.nothing.modules.crawlers.leetcode

import com.nothing.helper.annotations.springcomponents.InjectableService
import com.nothing.modules.crawlers.api.RankingFetcher
import com.nothing.modules.crawlers.api.UserStanding

@InjectableService class LCRankingFetcher implements RankingFetcher {
    @Override boolean needUpdate() { false }
    @Override List<UserStanding> fetchNew() { [] }
    @Override List<UserStanding> fetchExisting() { [] }
}
