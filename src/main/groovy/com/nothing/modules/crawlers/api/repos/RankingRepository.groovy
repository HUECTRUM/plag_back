package com.nothing.modules.crawlers.api.repos


import com.nothing.modules.crawlers.api.data.UserStanding

interface RankingRepository {
    List<UserStanding> getByContestId(String id)
    void save(String cId, List<UserStanding> data)
}