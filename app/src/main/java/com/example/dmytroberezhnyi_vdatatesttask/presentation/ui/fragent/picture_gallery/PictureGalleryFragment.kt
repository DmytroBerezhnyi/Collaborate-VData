package com.example.dmytroberezhnyi_vdatatesttask.presentation.ui.fragent.picture_gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.dmytroberezhnyi_vdatatesttask.R
import com.example.dmytroberezhnyi_vdatatesttask.data.pojo.Item
import com.example.dmytroberezhnyi_vdatatesttask.databinding.PictureGalleryFragmentBinding
import com.example.dmytroberezhnyi_vdatatesttask.presentation.base.architecture.BaseFragment
import com.example.dmytroberezhnyi_vdatatesttask.presentation.ui.adapter.PictureRecyclerAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PictureGalleryFragment : BaseFragment<PictureGalleryFragmentBinding>(),
    PictureRecyclerAdapter.OnPictureChosenListener {

    companion object {

        const val requestKey = "requestKey"

        const val urlKey = "pictureUrl"
    }

    private val viewModel: PictureGalleryViewModel by viewModels()

    override val layoutId: Int
        get() = R.layout.picture_gallery_fragment

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecycler()
    }

    private fun setupRecycler() {
        val adapter = PictureRecyclerAdapter(this)
        binding.rvPictures.adapter = adapter
        viewModel.pictures.observe(viewLifecycleOwner) {
            it?.let {
                adapter.setItems(it)
            }
        }
    }

    override fun onPictureChosen(item: Item) {
        setFragmentResult(requestKey, bundleOf(Pair(urlKey, item.link)))
        findNavController().navigateUp()
    }
}