package com.example.dmytroberezhnyi_vdatatesttask.data.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
data class Collaborator(
    @PrimaryKey(autoGenerate = true)
    val collaboratorId: Long? = null,
    var name: String,
    var surname: String,
    var avatarUrl: String
) : Parcelable