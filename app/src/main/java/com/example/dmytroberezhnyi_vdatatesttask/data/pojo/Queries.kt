package com.example.dmytroberezhnyi_vdatatesttask.data.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Queries {

    @SerializedName("request")
    @Expose
    var request: List<Request>? = null

    @SerializedName("nextPage")
    @Expose
    var nextPage: List<NextPage>? = null
}