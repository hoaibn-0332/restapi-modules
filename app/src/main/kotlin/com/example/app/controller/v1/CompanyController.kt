package com.example.app.controller.v1

import com.example.app.service.CompanyService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class CompanyController constructor(
    @Autowired private val companyService: CompanyService
) {

    @GetMapping("/company/all")
    fun all(): ResponseEntity<Any> {
        return ResponseEntity.ok(companyService.getAllCompanies())
    }
}
