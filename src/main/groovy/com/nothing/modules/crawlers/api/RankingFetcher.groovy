package com.nothing.modules.crawlers.api


import com.nothing.modules.crawlers.api.data.UserStanding

interface RankingFetcher {
    List<UserStanding> fetchNew(String cId)
}