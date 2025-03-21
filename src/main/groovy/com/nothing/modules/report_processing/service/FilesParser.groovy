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
        def pairsD = readCsv(pairsR), filesD = readCsv(filesR)
        def metaD = readMetadata(standings)

        def parsedPairs = pairsD.collect{
            [lId: it.leftFileId, lFile: it.leftFilePath, rId: it.rightFileId, rFile: it.rightFilePath,
             similarity: it.similarity as double, totOverlap: it.totalOverlap as int,
             longestOverlap: it.longestFragment as int] as Pair
        }

        def parsedMetadata = filesD.collectEntries {
            def sId = it.path.split('\\.')[0]

            [it. id, [id: it.id, fileName: it.path, sourceCode: it.content,
                      author: metaD[sId], submissionId: sId] as Metadata]
        }

        Tuple.tuple(parsedPairs, parsedMetadata)
    }

    List<Map<String, String>> readCsv(CSVReader reader) {
        List<Map<String, String>> allElements = new LinkedList<>()
        String[] headers = reader.readNext()

        while (reader.hasNext) {
            String[] nextLineAsTokens = reader.readNext()
            if (nextLineAsTokens != null) {
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
