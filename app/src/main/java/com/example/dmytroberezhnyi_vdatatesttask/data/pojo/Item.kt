package com.example.dmytroberezhnyi_vdatatesttask.data.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Item {

    @SerializedName("kind")
    @Expose
    var kind: String? = null

    @SerializedName("title")
    @Expose
    var title: String? = null

    @SerializedName("htmlTitle")
    @Expose
    var htmlTitle: String? = null

    @SerializedName("link")
    @Expose
    var link: String? = null

    @SerializedName("displayLink")
    @Expose
    var displayLink: String? = null

    @SerializedName("snippet")
    @Expose
    var snippet: String? = null

    @SerializedName("htmlSnippet")
    @Expose
    var htmlSnippet: String? = null

    @SerializedName("mime")
    @Expose
    var mime: String? = null

    @SerializedName("image")
    @Expose
    var image: Image? = null

    override fun toString(): String {
        return "Item(kind=$kind, " +
                "title=$title, " +
                "htmlTitle=$htmlTitle, " +
                "link=$link, " +
                "displayLink=$displayLink, " +
                "snippet=$snippet, " +
                "htmlSnippet=$htmlSnippet, " +
                "mime=$mime, " +
                "image=$image)"
    }
}