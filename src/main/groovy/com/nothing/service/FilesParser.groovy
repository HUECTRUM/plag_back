package com.nothing.service

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.node.ArrayNode
import com.nothing.annotations.springcomponents.InjectableService
import com.nothing.data.Metadata
import com.nothing.data.Pair
import com.opencsv.CSVReader

@InjectableService class FilesParser {
    private static final def jsonMapper = new ObjectMapper()

    Tuple2<List<Pair>, Map<String, Metadata>> processReport(CSVReader pairsR, CSVReader filesR, FileReader metadataR) {
        def pairsD = readCsv(pairsR), filesD = readCsv(filesR)
        def metaD = readMetadata(metadataR)

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
        def allLines = reader.readAll()

        (1..<allLines.size()).collect { line ->
            (0..<allLines[0].size()).collectEntries { idx ->
                [allLines[0][idx], allLines[line][idx]]
            }
        }
    }

    Map<String, String> readMetadata(FileReader fileReader) {
        def rootNode = jsonMapper.readTree(fileReader.readLines().join('')) as ArrayNode
        (0..<rootNode.size()).collectEntries {
            def ch = rootNode.get(it)
            [ch['s'].textValue(), ch['a'].textValue()]
        }
    }
}
