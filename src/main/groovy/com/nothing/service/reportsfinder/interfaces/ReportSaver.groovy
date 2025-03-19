package com.nothing.service.reportsfinder.interfaces

import com.fasterxml.jackson.databind.ObjectMapper
import com.nothing.data.Metadata
import com.nothing.data.Similarity

interface ReportSaver {
    void save(List<List<Map<String, Tuple2<Metadata, List<Similarity>>>>> clusters)
}