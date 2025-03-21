package com.nothing.modules.endpoints.service

import com.nothing.helper.annotations.springcomponents.InjectableService
import groovy.json.JsonSlurper

import static com.nothing.modules.endpoints.utils.ResourceUtils.resource

@InjectableService class ReportService {
    private static final def slurper = new JsonSlurper()

    def buildFinalReports() {
        def problems = []
        resource('reports/LC-weekly-contest-441').listFiles().each { probFolder ->
            def files = probFolder.listFiles()

            def probF = files.find { it.name.contains('prob') }, reportF = files.find { it.name.contains('report') }
            def probD = slurper.parseText(probF.text), reportD = slurper.parseText(reportF.text)

            problems.add([name: probD.name, data: reportD.data])
        }
        [problems: problems]
    }
}
