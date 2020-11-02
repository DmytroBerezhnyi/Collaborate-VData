package com.example.dmytroberezhnyi_vdatatesttask.data.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class SearchInformation {

    @SerializedName("searchTime")
    @Expose
    var searchTime: Double? = null

    @SerializedName("formattedSearchTime")
    @Expose
    var formattedSearchTime: String? = null

    @SerializedName("totalResults")
    @Expose
    var totalResults: String? = null

    @SerializedName("formattedTotalResults")
    @Expose
    var formattedTotalResults: String? = null
}