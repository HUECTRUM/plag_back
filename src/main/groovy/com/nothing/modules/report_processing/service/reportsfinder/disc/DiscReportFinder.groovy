package com.nothing.modules.report_processing.service.reportsfinder.disc

import com.nothing.helper.annotations.springcomponents.InjectableService
import com.nothing.modules.report_processing.service.reportsfinder.interfaces.ReportsFinder
import com.opencsv.CSVReader
import org.springframework.beans.factory.annotation.Value

@InjectableService class DiscReportFinder implements ReportsFinder {
    @Value("${finder.folder}") String folder

    @Override List<List> filesToProcess() {
        def folders = new File(folder).listFiles()

        folders.collect {
            def absPath = it.absolutePath
            [
                    new CSVReader(new FileReader("$absPath/pairs.csv")),
                    new CSVReader(new FileReader("$absPath/files.csv")),
            ]
        }
    }
}
