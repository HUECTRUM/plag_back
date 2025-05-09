package com.nothing.modules.report_processing.service.reportsfinder

import com.nothing.helper.alg.DSU
import com.nothing.helper.annotations.springcomponents.InjectableService
import com.nothing.modules.report_processing.data.Metadata
import com.nothing.modules.report_processing.data.Pair
import com.nothing.modules.report_processing.data.Similarity
import com.nothing.modules.report_processing.service.reportsfinder.interfaces.SubmissionsClustering

@InjectableService class DSUClustering implements SubmissionsClustering {
    private static double threshold = 0.85

    @Override
    List<Map<String, Tuple2<Metadata, List<Similarity>>>> getClusters(Tuple2<List<Pair>, Map<String, Metadata>> data) {
        def (pairs, metadata) = data

        DSU dsu = new DSU(metadata.size())
        pairs.findAll { inCluster(it) }.each {
            def similarities = attachments(it)
            dsu.unite(it.lId as int, it.rId as int, similarities[0], similarities[1])
        }

        List<List<String>> idClusters = new ArrayList<>()
        (0..<metadata.size()).each { idClusters.add(new ArrayList<>()) }
        (0..<metadata.size()).each { idClusters[dsu.find(it)].add(it) }

        List result = []
        idClusters
                .findAll { it.size() > 0 }
                .each { dsuCluster ->
                    def map = dsuCluster.collectEntries { id ->
                        [id, new Tuple2(metadata[id as String], dsu.data[id])]
                    }
                    result += map
                }

        return result
    }

    Tuple2 attachments(Pair pair) {
        new Tuple2(
                [id: pair.rId, similarity: pair.similarity,
                 totOverlap: pair.totOverlap, longestOverlap: pair.longestOverlap] as Similarity,
                [id: pair.lId, similarity: pair.similarity,
                 totOverlap: pair.totOverlap, longestOverlap: pair.longestOverlap] as Similarity,
        )
    }

    boolean inCluster(Pair pair) { pair.similarity - threshold > 1e-9 }
}
