package com.example.dmytroberezhnyi_vdatatesttask.presentation.ui.fragent.add_company

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.example.dmytroberezhnyi_vdatatesttask.data.entity.Company
import com.example.dmytroberezhnyi_vdatatesttask.data.repository.CompanyRepository

class AddCompanyViewModel @ViewModelInject constructor(
    private val repository: CompanyRepository
) : ViewModel() {

    fun addCompany(company: Company) {
        repository.insertCompany(company)
    }
}