package com.example.dmytroberezhnyi_vdatatesttask.presentation.ui.fragent.creation_collaborator

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dmytroberezhnyi_vdatatesttask.data.entity.Collaborator
import com.example.dmytroberezhnyi_vdatatesttask.data.entity.Company
import com.example.dmytroberezhnyi_vdatatesttask.data.repository.CompanyRepository

class CreationCollaboratorViewModel @ViewModelInject constructor(
    val repository: CompanyRepository
) : ViewModel() {

    private val _companyPosition = MutableLiveData(-1)

    val companyPosition: LiveData<Int> = _companyPosition

    val companies = repository.getCompanies()

    fun addCollaborator(collaborator: Collaborator, company: Company) {
        repository.insertCollaboratorWithCompany(collaborator, company)
    }

    fun saveCompanyPosition(position: Int) {
        _companyPosition.value = position
    }
}