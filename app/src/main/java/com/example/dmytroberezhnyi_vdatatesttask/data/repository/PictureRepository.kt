package com.example.dmytroberezhnyi_vdatatesttask.data.repository

import androidx.lifecycle.liveData
import com.example.dmytroberezhnyi_vdatatesttask.api.PictureService
import com.example.dmytroberezhnyi_vdatatesttask.data.pojo.Item
import javax.inject.Inject

class PictureRepository @Inject constructor(
    private val pictureService: PictureService
) {

    private val key = "AIzaSyAeukwlzQm3kJOKFT29jsZbES2cdG62ZLc"
    private val searchType = "image"
    private val cx = "018013627651465683176:4zpb_umhuas"

    fun searchImages(query: String) = liveData<List<Item>> {
        val response = pictureService.getExamples(key, cx, searchType, query)
        if (response.isSuccessful) {
            emit(response.body()?.items!!)
        }
    }
}