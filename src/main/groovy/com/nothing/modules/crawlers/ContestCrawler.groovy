package com.nothing.modules.crawlers

import com.nothing.helper.annotations.springcomponents.InjectableService
import com.nothing.modules.crawlers.api.ContestInfoFetcher
import com.nothing.modules.crawlers.api.SubmissionFetcher
import com.nothing.modules.crawlers.api.repos.ContestInfoRepository
import com.nothing.modules.crawlers.api.RankingFetcher
import com.nothing.modules.crawlers.api.repos.RankingRepository
import com.nothing.modules.crawlers.api.data.ContestMetadata
import com.nothing.modules.crawlers.api.data.UserStanding
import com.nothing.modules.crawlers.api.repos.SubmissionRepository

import javax.annotation.PostConstruct

@InjectableService class ContestCrawler {
    public final ContestInfoFetcher contestInfoFetcher
    public final ContestInfoRepository contestInfoRepository
    public final RankingRepository rankingRepository
    public final RankingFetcher rankingFetcher
    public final SubmissionRepository submissionRepository
    public final SubmissionFetcher submissionFetcher

    void runCrawler() {
        def latestName = contestInfoFetcher.latestName()

        //todo: need?
        ContestMetadata metadata = contestInfoRepository.getById(latestName)
        if (metadata == null) {
            log.info("Fetching new contest info for ${latestName}")

            metadata = contestInfoFetcher.fetchInfo(latestName)
            contestInfoRepository.save(metadata)
        }

        List<UserStanding> standings = rankingRepository.getByContestId(latestName)
        if (standings == null) {
            log.info("Fetching new rankings for ${latestName}")
            standings = rankingFetcher.fetchNew(latestName)
            rankingRepository.save(latestName, standings)
        }

        standings.each { standing ->
            log.info("Processing user ${standing.name} rank ${standing.rank}")

            standing.submissions.each { sub ->
                if (!submissionRepository.exists(latestName, sub.probId, sub.id)) {
                    log.info("Saving code for problem ${sub.probId} sid ${sub.id}")

                    def code = submissionFetcher.fetchCode(latestName, sub.probId, sub.id, sub.additionalInfo)
                    submissionRepository.save(latestName, sub.probId, sub.id, code)
                }
            }
        }
    }

    @PostConstruct void go() { runCrawler() }
}
