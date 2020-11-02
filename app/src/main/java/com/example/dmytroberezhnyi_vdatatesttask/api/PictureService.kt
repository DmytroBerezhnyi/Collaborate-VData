package com.example.dmytroberezhnyi_vdatatesttask.api

import com.example.dmytroberezhnyi_vdatatesttask.data.pojo.Example
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PictureService {

    @GET("customsearch/v1")
    suspend fun getExamples(
        @Query("key") key: String,
        @Query("cx") cx: String,
        @Query("searchType") searchType: String,
        @Query("q") q: String
    ): Response<Example>
}