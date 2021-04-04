package com.example.dmytroberezhnyi_vdatatesttask.presentation.ui.fragent.company

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.dmytroberezhnyi_vdatatesttask.data.entity.Company
import com.example.dmytroberezhnyi_vdatatesttask.data.repository.CompanyRepository

class CompanyViewModel @ViewModelInject constructor(
    val repository: CompanyRepository
) : ViewModel() {

    val companies: LiveData<List<Company>> = repository.getCompanies()

    fun deleteCompany(company: Company) {
        repository.deleteCompany(company)
    }
}