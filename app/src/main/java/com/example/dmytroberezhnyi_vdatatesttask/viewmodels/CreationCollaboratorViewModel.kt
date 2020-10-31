package com.example.dmytroberezhnyi_vdatatesttask.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dmytroberezhnyi_vdatatesttask.data.entity.Company

class CreationCollaboratorViewModel : ViewModel() {

    private val mCompanies = listOf(
        Company(0, "RiotCorp"),
        Company(1, "RiotCorp2"),
        Company(2, "RiotCorp3"),
        Company(3, "RiotCorp4")
    )

    private val _companies = MutableLiveData(mCompanies)

    val companies: LiveData<List<Company>> = _companies
}