package com.example.dmytroberezhnyi_vdatatesttask.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.example.dmytroberezhnyi_vdatatesttask.data.entity.Collaborator
import com.example.dmytroberezhnyi_vdatatesttask.data.entity.Company
import com.example.dmytroberezhnyi_vdatatesttask.data.repository.CompanyRepository

class CreationCollaboratorViewModel @ViewModelInject constructor(
    val repository: CompanyRepository
) : ViewModel() {
    fun addCollaborator(collaborator: Collaborator, company: Company) {
        repository.insertCollaboratorWithCompany(collaborator, company)
    }

    val companies = repository.getCompanies()
}