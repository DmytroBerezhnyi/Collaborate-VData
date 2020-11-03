package com.example.dmytroberezhnyi_vdatatesttask.data.entity

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Collaborator(
    @PrimaryKey(autoGenerate = true)
    val collaboratorId: Long? = null,
    var name: String,
    var surname: String,
    var avatarUrl: String
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readValue(Long::class.java.classLoader) as? Long,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(collaboratorId)
        parcel.writeString(name)
        parcel.writeString(surname)
        parcel.writeString(avatarUrl)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Collaborator> {
        override fun createFromParcel(parcel: Parcel): Collaborator {
            return Collaborator(parcel)
        }

        override fun newArray(size: Int): Array<Collaborator?> {
            return arrayOfNulls(size)
        }
    }
}