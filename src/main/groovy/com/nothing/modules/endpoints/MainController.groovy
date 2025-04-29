package com.nothing.modules.endpoints

import com.nothing.helper.annotations.springcomponents.InjectableController
import com.nothing.modules.endpoints.service.ReportService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@InjectableController class MainController {
    public final ReportService reportService

    @GetMapping("/report/{name}") ResponseEntity getEmployeeById(@PathVariable('name') String name) {
        def problems = reportService.buildFinalReports(name)
        ResponseEntity.ok().body(problems)
    }
}
