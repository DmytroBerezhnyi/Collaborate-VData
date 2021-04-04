package com.example.dmytroberezhnyi_vdatatesttask.presentation.ui.fragent.collaborator

import androidx.lifecycle.ViewModel
import com.example.dmytroberezhnyi_vdatatesttask.data.repository.CompanyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CollaboratorViewModel @Inject constructor(
    val repository: CompanyRepository
) : ViewModel() {

    val collaboratorsWithCompanies = repository.getCollaboratorsWithCompanies()
}