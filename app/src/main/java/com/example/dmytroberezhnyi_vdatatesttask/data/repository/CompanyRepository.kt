package com.example.dmytroberezhnyi_vdatatesttask.data.repository

import androidx.lifecycle.LiveData
import com.example.dmytroberezhnyi_vdatatesttask.data.entity.Collaborator
import com.example.dmytroberezhnyi_vdatatesttask.data.entity.CollaboratorWithCompanies
import com.example.dmytroberezhnyi_vdatatesttask.data.entity.Company
import com.example.dmytroberezhnyi_vdatatesttask.data.entity.CompanyCollaboratorContract
import com.example.dmytroberezhnyi_vdatatesttask.data.local.CollaboratorDao
import com.example.dmytroberezhnyi_vdatatesttask.data.local.CompanyCollaboratorDao
import com.example.dmytroberezhnyi_vdatatesttask.data.local.CompanyDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class CompanyRepository @Inject constructor(
    private val localCompanyDataSource: CompanyDao,
    private val localCompanyCollabDataSource: CompanyCollaboratorDao,
    private val localCollaboratorDataSource: CollaboratorDao
) {

    fun getCompanies(): LiveData<List<Company>> {
        return localCompanyDataSource.getCompanies()
    }

    fun insertCompany(company: Company) {
        CoroutineScope(Dispatchers.IO).launch {
            localCompanyDataSource.insert(company)
        }
    }

    fun deleteCompany(company: Company) {
        CoroutineScope(Dispatchers.IO).launch {
            localCompanyDataSource.delete(company)
        }
    }

    fun getCollaboratorsWithCompanies(): LiveData<List<CollaboratorWithCompanies>> {
        return localCollaboratorDataSource.getCollaboratorsWithCompanies()
    }

    fun insertCollaboratorWithCompany(collaborator: Collaborator, company: Company) {
        CoroutineScope(Dispatchers.IO).launch {
            val collaboratorId = localCollaboratorDataSource.insert(collaborator)
            val companyCollaboratorContract =
                CompanyCollaboratorContract(company.companyId!!, collaboratorId)
            localCompanyCollabDataSource.insert(companyCollaboratorContract)
        }
    }

    fun addCompanyForCollaborator(collaborator: Collaborator, company: Company) {
        CoroutineScope(Dispatchers.IO).launch {
            val companyCollaboratorContract =
                CompanyCollaboratorContract(company.companyId!!, collaborator.collaboratorId!!)
            localCompanyCollabDataSource.insert(companyCollaboratorContract)
        }
    }
}