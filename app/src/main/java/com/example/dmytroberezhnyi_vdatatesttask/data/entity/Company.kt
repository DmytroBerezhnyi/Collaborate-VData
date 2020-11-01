package com.example.dmytroberezhnyi_vdatatesttask.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Company(
    @PrimaryKey(autoGenerate = true)
    var companyId: Long? = null,
    var companyName: String
) {
    override fun toString(): String {
        return companyName
    }
}