package com.example.dmytroberezhnyi_vdatatesttask.presentation.ui.fragent.company

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.dmytroberezhnyi_vdatatesttask.data.entity.Company
import com.example.dmytroberezhnyi_vdatatesttask.data.repository.CompanyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CompanyViewModel @Inject constructor(
    val repository: CompanyRepository
) : ViewModel() {

    val companies: LiveData<List<Company>> = repository.getCompanies()

    fun deleteCompany(company: Company) {
        repository.deleteCompany(company)
    }
}