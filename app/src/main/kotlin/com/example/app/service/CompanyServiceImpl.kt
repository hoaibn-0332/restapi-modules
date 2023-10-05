package com.example.app.service

import com.example.core.database.CompanyRepository
import com.example.core.database.model.toCompany
import com.example.core.model.Company
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CompanyServiceImpl constructor(
    @Autowired private val companyRepository: CompanyRepository
) : CompanyService {
    override fun getAllCompanies(): List<Company> {
        return companyRepository.findAll().map { it.toCompany() }
    }
}
