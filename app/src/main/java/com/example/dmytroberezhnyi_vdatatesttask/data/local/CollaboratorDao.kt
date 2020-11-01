package com.example.dmytroberezhnyi_vdatatesttask.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.dmytroberezhnyi_vdatatesttask.data.entity.Collaborator
import com.example.dmytroberezhnyi_vdatatesttask.data.entity.CollaboratorWithCompanies

@Dao
interface CollaboratorDao {

    @Transaction
    @Query("SELECT * FROM Collaborator")
    fun getCollaboratorsWithCompanies(): LiveData<List<CollaboratorWithCompanies>>

    @Transaction
    @Query("SELECT * FROM Collaborator WHERE :collaboratorId")
    fun getCollaboratorWithCompanies(collaboratorId: Long): LiveData<CollaboratorWithCompanies>

    @Query("SELECT * FROM Collaborator")
    fun getCollaborators(): LiveData<List<Collaborator>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(collaborator: Collaborator): Long
}