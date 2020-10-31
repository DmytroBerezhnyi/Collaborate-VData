package com.example.dmytroberezhnyi_vdatatesttask.data.entity

data class Company(
    val id: Int,
    var companyName: String
) {
    override fun toString(): String {
        return companyName
    }
}