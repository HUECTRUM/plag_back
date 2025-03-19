package com.nothing.service.reportsfinder.disc

import com.fasterxml.jackson.databind.ObjectMapper
import com.nothing.annotations.springcomponents.InjectableService
import com.nothing.data.Metadata
import com.nothing.data.Similarity
import com.nothing.service.reportsfinder.interfaces.ReportSaver

@InjectableService class DiscReportSaver implements ReportSaver {
    private static final def jsonMapper = new ObjectMapper()
    static final def path = "D:\\CF1\\be_fin\\report.json"

    @Override void save(List<List<Map<String, Tuple2<Metadata, List<Similarity>>>>> clusters) {
        def file = new File(path)
        file.createNewFile()
        file.write(jsonMapper.writeValueAsString(clusters))
    }
}
