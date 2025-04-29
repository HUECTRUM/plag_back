package com.nothing.modules.report_processing.service.reportsfinder.interfaces


import com.nothing.modules.report_processing.data.Metadata
import com.nothing.modules.report_processing.data.Similarity

interface ReportSaver {
    void save(String key, List<List<Map<String, Tuple2<Metadata, List<Similarity>>>>> clusters)
}