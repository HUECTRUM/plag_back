package com.nothing.modules.report_processing.service

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.node.ArrayNode
import com.nothing.helper.annotations.springcomponents.InjectableService
import com.nothing.modules.crawlers.api.data.UserStanding
import com.nothing.modules.report_processing.data.Metadata
import com.nothing.modules.report_processing.data.Pair
import com.opencsv.CSVReader

@InjectableService class FilesParser {
    private static final def jsonMapper = new ObjectMapper()

    Tuple2<List<Pair>, Map<String, Metadata>> processReport(CSVReader pairsR, CSVReader filesR, List<UserStanding> standings) {
        log.info("0")
        def pairsD = readCsv(pairsR), filesD = readCsv(filesR)
        log.info("0.5")
        def metaD = readMetadata(standings)

        log.info("1")
        def parsedPairs = pairsD.collect{
            [lId: it.leftFileId, lFile: it.leftFilePath, rId: it.rightFileId, rFile: it.rightFilePath,
             similarity: it.similarity as double, totOverlap: it.totalOverlap as int,
             longestOverlap: it.longestFragment as int] as Pair
        }

        log.info("2")
        def parsedMetadata = filesD.collectEntries {
            def sId = it.path.split('\\.')[0]

            [it. id, [id: it.id, fileName: it.path, sourceCode: it.content,
                      author: metaD[sId], submissionId: sId] as Metadata]
        }

        log.info("3")
        Tuple.tuple(parsedPairs, parsedMetadata)
    }

    List<Map<String, String>> readCsv(CSVReader reader) {
        log.info("00")

        List<Map<String, String>> allElements = new LinkedList<>()
        String[] headers = reader.readNext()

        int i = -1
        while (reader.hasNext) {
            String[] nextLineAsTokens = reader.readNext()
            if (nextLineAsTokens != null) {
                i += 1
                if (i % 1000 == 0) log.info("Processing line $i")
                allElements.add(new HashMap<>())

                for (int j = 0; j < headers.size(); ++j) allElements[i].put(headers[j], nextLineAsTokens[j])
            }
        }

        return allElements
    }

    Map<String, String> readMetadata(List<UserStanding> standings) {
        def result = [:]
        standings.each { standing ->
            standing.submissions.each { result[it.id] = standing.name}
        }
        return result
    }
}
