package com.nothing.modules.report_processing.service.reportsfinder.disc

import com.fasterxml.jackson.databind.ObjectMapper
import com.nothing.helper.annotations.springcomponents.InjectableService
import com.nothing.modules.report_processing.data.Metadata
import com.nothing.modules.report_processing.data.Similarity
import com.nothing.modules.report_processing.service.reportsfinder.interfaces.ReportSaver
import org.springframework.beans.factory.annotation.Value

class DiscReportSaver implements ReportSaver {
    private static final def jsonMapper = new ObjectMapper()

    @Value("${saver.path}") String path

    @Override void save(String key, List<List<Map<String, Tuple2<Metadata, List<Similarity>>>>> clusters) {
        def file = new File(path)
        def toWrite = [data: clusters]
        file.createNewFile()
        file.write(jsonMapper.writeValueAsString(toWrite))
    }
}
