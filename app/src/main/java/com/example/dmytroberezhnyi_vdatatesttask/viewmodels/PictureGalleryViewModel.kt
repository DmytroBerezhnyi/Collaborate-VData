package com.example.dmytroberezhnyi_vdatatesttask.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.dmytroberezhnyi_vdatatesttask.data.pojo.Item
import com.example.dmytroberezhnyi_vdatatesttask.data.repository.PictureRepository

class PictureGalleryViewModel @ViewModelInject constructor(
    pictureRepository: PictureRepository
) : ViewModel() {

    // TODO: 02.11.20 only 10 pictures per one page. WorkManager is coming...
    val pictures: LiveData<List<Item>> = pictureRepository.searchImages("cat")
}