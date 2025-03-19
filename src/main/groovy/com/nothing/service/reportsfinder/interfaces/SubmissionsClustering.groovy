package com.nothing.service.reportsfinder.interfaces

import com.nothing.data.Metadata
import com.nothing.data.Pair
import com.nothing.data.Similarity

interface SubmissionsClustering {
    List<List<Map<String, Tuple2<Metadata, List<Similarity>>>>> getClusters(Tuple2<List<Pair>, Map<String, Metadata>> data)
}