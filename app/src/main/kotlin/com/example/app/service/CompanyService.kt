package com.example.app.service

import com.example.core.model.Company

interface CompanyService {
    fun getAllCompanies(): List<Company>
}
