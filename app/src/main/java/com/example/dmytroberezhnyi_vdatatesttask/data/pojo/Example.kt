package com.example.dmytroberezhnyi_vdatatesttask.data.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Example {

    @SerializedName("kind")
    @Expose
    var kind: String? = null

    @SerializedName("url")
    @Expose
    var url: Url? = null

    @SerializedName("queries")
    @Expose
    var queries: Queries? = null

    @SerializedName("context")
    @Expose
    var context: Context? = null

    @SerializedName("searchInformation")
    @Expose
    var searchInformation: SearchInformation? = null

    @SerializedName("items")
    @Expose
    var items: List<Item>? = null
}