package com.example.dmytroberezhnyi_vdatatesttask.presentation.ui.fragent.add_company

import androidx.lifecycle.ViewModel
import com.example.dmytroberezhnyi_vdatatesttask.data.entity.Company
import com.example.dmytroberezhnyi_vdatatesttask.data.repository.CompanyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddCompanyViewModel @Inject constructor(
    private val repository: CompanyRepository
) : ViewModel() {

    fun addCompany(company: Company) {
        repository.insertCompany(company)
    }
}