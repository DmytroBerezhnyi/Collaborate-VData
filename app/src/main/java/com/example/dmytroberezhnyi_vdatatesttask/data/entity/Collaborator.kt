package com.example.dmytroberezhnyi_vdatatesttask.data.entity

data class Collaborator(
    val id: Long,
    var name: String,
    var surname: String,
    var avatarUrl: String,
    val companies: List<Company>
)