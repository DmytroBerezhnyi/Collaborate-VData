package com.example.dmytroberezhnyi_vdatatesttask.presentation.ui.fragent.picture_gallery

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.dmytroberezhnyi_vdatatesttask.data.pojo.Item
import com.example.dmytroberezhnyi_vdatatesttask.data.repository.PictureRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PictureGalleryViewModel @Inject constructor(
    pictureRepository: PictureRepository
) : ViewModel() {

    // TODO: 02.11.20 only 10 pictures per one page. WorkManager is coming...
    val pictures: LiveData<List<Item>> = pictureRepository.searchImages("cat")
}