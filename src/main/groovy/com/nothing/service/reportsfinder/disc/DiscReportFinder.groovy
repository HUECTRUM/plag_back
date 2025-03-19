package com.nothing.service.reportsfinder.disc

import com.nothing.annotations.springcomponents.InjectableService
import com.nothing.service.reportsfinder.interfaces.ReportsFinder
import com.opencsv.CSVReader

@InjectableService class DiscReportFinder implements ReportsFinder {
    static final def folder = "D:\\CF1\\be_reports"

    @Override List<List> filesToProcess() {
        def folders = new File(folder).listFiles()

        folders.collect {
            def absPath = it.absolutePath
            [
                    new CSVReader(new FileReader("$absPath/pairs.csv")),
                    new CSVReader(new FileReader("$absPath/files.csv")),
                    new FileReader("$absPath/metadata.json")
            ]
        }
    }
}
