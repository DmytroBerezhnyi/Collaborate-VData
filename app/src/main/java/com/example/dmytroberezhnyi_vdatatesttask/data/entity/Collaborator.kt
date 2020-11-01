package com.example.dmytroberezhnyi_vdatatesttask.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Collaborator(
    @PrimaryKey(autoGenerate = true)
    val collaboratorId: Long? = null,
    var name: String,
    var surname: String,
    var avatarUrl: String
)