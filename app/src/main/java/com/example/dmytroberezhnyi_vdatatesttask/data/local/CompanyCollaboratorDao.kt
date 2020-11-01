package com.example.dmytroberezhnyi_vdatatesttask.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.dmytroberezhnyi_vdatatesttask.data.entity.CompanyCollaboratorContract

@Dao
interface CompanyCollaboratorDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(companyCollaboratorContract: CompanyCollaboratorContract)
}