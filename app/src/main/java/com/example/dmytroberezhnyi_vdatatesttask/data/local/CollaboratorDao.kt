package com.example.dmytroberezhnyi_vdatatesttask.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.dmytroberezhnyi_vdatatesttask.data.entity.Collaborator
import com.example.dmytroberezhnyi_vdatatesttask.data.entity.CollaboratorWithCompanies
import com.example.dmytroberezhnyi_vdatatesttask.data.entity.Company

@Dao
interface CollaboratorDao {

    @Transaction
    @Query("SELECT * FROM collaborator")
    fun getCollaboratorsWithCompanies(): LiveData<List<CollaboratorWithCompanies>>

    @Transaction
    @Query("SELECT * FROM collaborator WHERE :collaboratorId")
    fun getCollaboratorWithCompanies(collaboratorId: Long): LiveData<CollaboratorWithCompanies>

    @Query("SELECT * FROM collaborator")
    fun getCollaborators(): LiveData<List<Collaborator>>

    @Query("SELECT * FROM collaborator " +
            "INNER JOIN CompanyCollaboratorContract as c ON " +
            "collaborator.collaboratorId = c.collaboratorId AND " +
            "c.isWorking = :isWorking AND " +
            "c.companyId = :companyId")
    fun getCollaboratorsCompany(companyId: Long, isWorking: Boolean) : LiveData<List<Collaborator>>

    @Query("SELECT COUNT(*) FROM collaborator " +
            "INNER JOIN CompanyCollaboratorContract as c ON " +
            "collaborator.collaboratorId = c.collaboratorId AND " +
            "c.companyId = :companyId")
    fun getCollaboratorsTotalCount(companyId: Long): LiveData<Int>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(collaborator: Collaborator): Long

    @Update
    suspend fun update(collaborator: Collaborator)
}