package com.nothing.modules.report_processing.service.reportsfinder.interfaces

import com.nothing.modules.report_processing.data.Metadata
import com.nothing.modules.report_processing.data.Pair
import com.nothing.modules.report_processing.data.Similarity

interface SubmissionsClustering {
    List<List<Map<String, Tuple2<Metadata, List<Similarity>>>>> getClusters(Tuple2<List<Pair>, Map<String, Metadata>> data)
}