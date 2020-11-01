package com.example.dmytroberezhnyi_vdatatesttask.data.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Junction
import androidx.room.Relation

@Entity(primaryKeys = ["companyId", "collaboratorId"])
data class CompanyCollaboratorContract(
    val companyId: Long,
    val collaboratorId: Long
)

data class CollaboratorWithCompanies(
    @Embedded val collaborator: Collaborator,
    @Relation(
        parentColumn = "collaboratorId",
        entityColumn = "companyId",
        associateBy = Junction(CompanyCollaboratorContract::class)
    )
    val companies: List<Company>
)

data class CompanyWithCollaborators(
    @Embedded val company: Company,
    @Relation(
        parentColumn = "companyId",
        entityColumn = "collaboratorId",
        associateBy = Junction(CompanyCollaboratorContract::class)
    )
    val collaborators: List<Collaborator>
)