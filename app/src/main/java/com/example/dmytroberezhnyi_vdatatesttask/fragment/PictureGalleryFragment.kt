package com.example.dmytroberezhnyi_vdatatesttask.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import com.example.dmytroberezhnyi_vdatatesttask.R
import com.example.dmytroberezhnyi_vdatatesttask.adapters.PictureRecyclerAdapter
import com.example.dmytroberezhnyi_vdatatesttask.adapters.PictureViewHolder
import com.example.dmytroberezhnyi_vdatatesttask.data.pojo.Item
import com.example.dmytroberezhnyi_vdatatesttask.viewmodels.PictureGalleryViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.picture_gallery_fragment.*

@AndroidEntryPoint
class PictureGalleryFragment : BaseFragment(),
    PictureViewHolder.OnPictureChosenListener {

    private val viewModel: PictureGalleryViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.picture_gallery_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecycler()
    }

    private fun setupRecycler() {
        val adapter = PictureRecyclerAdapter(this)
        rvPictures.adapter = adapter
        viewModel.pictures.observe(viewLifecycleOwner, {
            it?.let {
                adapter.setItems(it)
            }
        })
    }

    override fun onPictureChosen(item: Item) {
        val bundle = bundleOf(Pair(CreationCollaboratorFragment.urlKey, item.link))
        navigate(R.id.action_pictureGalleryFragment_to_creationCollaboratorFragment, bundle)
    }
}