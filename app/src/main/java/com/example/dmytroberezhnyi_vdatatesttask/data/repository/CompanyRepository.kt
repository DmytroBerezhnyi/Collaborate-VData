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

    // TODO: 03.11.20 working! but reactive data changing is lost.. fix it
    //  suspend fun getCollaboratorsWithCompanies(): List<CollaboratorWithCompanies>
    /*fun getCollaboratorsWithCompanies() = liveData {
        val collaboratorWithCompanies = localCollaboratorDataSource.getCollaboratorsWithCompanies()
        collaboratorWithCompanies.forEach {
            val currentCompanies =
                localCompanyDataSource.getCompaniesSus(it.collaborator.collaboratorId!!, true)
            it.companies = currentCompanies
        }
        emitSource(MutableLiveData(collaboratorWithCompanies))
    }*/

    fun getCollaboratorsWithCompanies(): LiveData<List<CollaboratorWithCompanies>> {
        return localCollaboratorDataSource.getCollaboratorsWithCompanies()
    }

    fun insertCollaboratorWithCompany(collaborator: Collaborator, company: Company) {
        CoroutineScope(Dispatchers.IO).launch {
            val collaboratorId = localCollaboratorDataSource.insert(collaborator)
            val companyCollaboratorContract =
                CompanyCollaboratorContract(company.companyId!!, collaboratorId, true)
            localCompanyCollabDataSource.insert(companyCollaboratorContract)
        }
    }

    fun getCompanyWithCollaborators(
        companyId: Long,
        isWorking: Boolean
    ): LiveData<List<Collaborator>> {
        return localCollaboratorDataSource.getCollaboratorsCompany(companyId, isWorking)
    }

    fun getCollaboratorsTotalCount(companyId: Long): LiveData<Int> {
        return localCollaboratorDataSource.getCollaboratorsTotalCount(companyId)
    }

    fun getCompanies(collaboratorId: Long, isWorking: Boolean): LiveData<List<Company>> {
        return localCompanyDataSource.getCompanies(collaboratorId, isWorking)
    }

    fun addCompanyForCollaborator(collaboratorId: Long, companyId: Long) {
        CoroutineScope(Dispatchers.IO).launch {
            val companyCollaboratorContract =
                CompanyCollaboratorContract(companyId, collaboratorId, true)
            localCompanyCollabDataSource.insert(companyCollaboratorContract)
        }
    }

    fun fireCollaboratorFromCompany(collaboratorId: Long, companyId: Long) {
        CoroutineScope(Dispatchers.IO).launch {
            val companyCollaboratorContract =
                CompanyCollaboratorContract(companyId, collaboratorId, false)
            localCompanyCollabDataSource.insert(companyCollaboratorContract)
        }
    }

    fun updateCollaborator(collaborator: Collaborator) {
        CoroutineScope(Dispatchers.IO).launch {
            localCollaboratorDataSource.update(collaborator)
        }
    }
}