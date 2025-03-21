package com.nothing.modules.report_processing

import com.nothing.helper.annotations.springcomponents.InjectableService
import com.nothing.modules.crawlers.api.ContestInfoFetcher
import com.nothing.modules.crawlers.api.repos.RankingRepository
import com.nothing.modules.report_processing.service.FilesParser
import com.nothing.modules.report_processing.service.reportsfinder.interfaces.ReportSaver
import com.nothing.modules.report_processing.service.reportsfinder.interfaces.ReportsFinder
import com.nothing.modules.report_processing.service.reportsfinder.interfaces.SubmissionsClustering

import javax.annotation.PostConstruct

@InjectableService class ReportRunOrchestrator {
    public final ReportsFinder reportsFinder
    public final FilesParser filesParser
    public final SubmissionsClustering clustering
    public final ReportSaver reportSaver
    public final RankingRepository rankingRepository
    public final ContestInfoFetcher contestInfoFetcher

    void run() {
        //todo: run dolos automatically
        def newReports = reportsFinder.filesToProcess()
        newReports.each { runOne(it) }
    }

    void runOne(List files) {
        def metadata = rankingRepository.getByContestId(contestInfoFetcher.latestName())
        def report = filesParser.processReport(files[0], files[1], metadata)
        def clusters = clustering.getClusters(report)
        clusters.findAll{ it.size() > 1 }.each { log.info("Cluster sz {}", it.size()) }
        reportSaver.save(clusters)
    }
}
