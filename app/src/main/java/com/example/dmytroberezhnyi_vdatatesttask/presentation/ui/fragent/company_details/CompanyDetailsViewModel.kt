package com.example.dmytroberezhnyi_vdatatesttask.presentation.ui.fragent.company_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.dmytroberezhnyi_vdatatesttask.data.entity.Collaborator
import com.example.dmytroberezhnyi_vdatatesttask.data.repository.CompanyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CompanyDetailsViewModel @Inject constructor(
    private val repository: CompanyRepository
) : ViewModel() {

    fun getCollaboratorsTotalCount(companyId: Long): LiveData<Int> {
        return repository.getCollaboratorsTotalCount(companyId)
    }

    fun getCompanyWithCollaborators(
        companyId: Long,
        isWorking: Boolean
    ): LiveData<List<Collaborator>> {
        return repository.getCompanyWithCollaborators(companyId, isWorking)
    }
}