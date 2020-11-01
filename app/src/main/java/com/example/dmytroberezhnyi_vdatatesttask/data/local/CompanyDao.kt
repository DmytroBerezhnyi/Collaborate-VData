package com.example.dmytroberezhnyi_vdatatesttask.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.dmytroberezhnyi_vdatatesttask.data.entity.Company
import com.example.dmytroberezhnyi_vdatatesttask.data.entity.CompanyWithCollaborators

@Dao
interface CompanyDao {

    @Transaction
    @Query("SELECT * FROM company")
    fun getCompaniesWithCollaborators(): LiveData<List<CompanyWithCollaborators>>

    @Transaction
    @Query("SELECT * FROM company WHERE :companyId")
    fun getCompanyWithCollaborators(companyId: Long): LiveData<CompanyWithCollaborators>

    @Query("SELECT * FROM Company")
    fun getCompanies(): LiveData<List<Company>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(company: Company)

    @Update
    suspend fun update(company: Company)

    @Delete
    suspend fun delete(company: Company)
}