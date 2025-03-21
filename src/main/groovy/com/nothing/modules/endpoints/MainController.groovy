package com.nothing.modules.endpoints

import com.nothing.helper.annotations.springcomponents.InjectableController
import com.nothing.modules.endpoints.service.ReportService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping

@InjectableController class MainController {
    public final ReportService reportService

    @GetMapping("/report") ResponseEntity getEmployeeById() {
        def problems = reportService.buildFinalReports()
        ResponseEntity.ok().body(problems)
    }
}
