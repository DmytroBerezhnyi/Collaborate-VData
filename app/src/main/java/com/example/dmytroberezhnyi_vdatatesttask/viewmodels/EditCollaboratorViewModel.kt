package com.example.dmytroberezhnyi_vdatatesttask.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dmytroberezhnyi_vdatatesttask.data.entity.Collaborator
import com.example.dmytroberezhnyi_vdatatesttask.data.entity.Company
import com.example.dmytroberezhnyi_vdatatesttask.data.repository.CompanyRepository

class EditCollaboratorViewModel @ViewModelInject constructor(
    private val repository: CompanyRepository
) : ViewModel() {

    fun getCompanies(collaboratorId: Long, isWorking: Boolean): LiveData<List<Company>> {
        return repository.getCompanies(collaboratorId, isWorking)
    }

    fun getAllAvailableCompaniesForCollab(): LiveData<List<Company>> {
        return repository.getCompanies()
    }

    fun addCompanyForCollaborator(collaboratorId: Long, companyId: Long) {
        repository.addCompanyForCollaborator(collaboratorId, companyId)
    }

    fun fireCollaboratorFromCompany(collaboratorId: Long, companyId: Long) {
        repository.fireCollaboratorFromCompany(collaboratorId, companyId)
    }

    fun updateCollaborator(collaborator: Collaborator) {
        repository.updateCollaborator(collaborator)
    }
}