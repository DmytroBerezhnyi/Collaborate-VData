package com.example.dmytroberezhnyi_vdatatesttask.presentation.ui.fragent.creation_collaborator

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.AdapterView
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.dmytroberezhnyi_vdatatesttask.R
import com.example.dmytroberezhnyi_vdatatesttask.data.entity.Collaborator
import com.example.dmytroberezhnyi_vdatatesttask.data.entity.Company
import com.example.dmytroberezhnyi_vdatatesttask.databinding.CreationCollaboratorFragmentBinding
import com.example.dmytroberezhnyi_vdatatesttask.presentation.base.architecture.BaseFragment
import com.example.dmytroberezhnyi_vdatatesttask.presentation.ui.adapter.CompanySpinnerAdapter
import com.example.dmytroberezhnyi_vdatatesttask.presentation.ui.fragent.picture_gallery.PictureGalleryFragment.Companion.requestKey
import com.example.dmytroberezhnyi_vdatatesttask.presentation.ui.fragent.picture_gallery.PictureGalleryFragment.Companion.urlKey
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreationCollaboratorFragment : BaseFragment<CreationCollaboratorFragmentBinding>() {

    override val layoutId: Int
        get() = R.layout.creation_collaborator_fragment

    private val viewModel: CreationCollaboratorViewModel by viewModels()

    private var pictureUrl: String? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.companies.observe(viewLifecycleOwner) {
            it?.let {
                setupViews(it)
            }
        }
    }

    private fun setupViews(companies: List<Company>) {
        var company: Company?
        val adapter =
            CompanySpinnerAdapter(requireContext(), R.layout.company_title_item, companies)

        binding.tvWithSpinnerBehavior.onItemClickListener =
            AdapterView.OnItemClickListener { adapterView, _, position, _ ->
                viewModel.saveCompanyPosition(position)
                company = adapterView?.getItemAtPosition(position) as Company
            }

        binding.tvWithSpinnerBehavior.setAdapter(adapter)

        binding.btnSave.setOnClickListener {
            val name = binding.tilName.text.toString()
            val surname = binding.tilSurname.text.toString()

            if (isFieldsFilled(name, surname)) {
                val collaborator = Collaborator(null, name, surname, pictureUrl!!)
                company = adapter.getItem(viewModel.companyPosition.value!!)

                viewModel.addCollaborator(collaborator, company!!)
                findNavController().popBackStack()
            }
        }

        binding.ivCollaboratorPhoto.setOnClickListener {
            findNavController().navigate(R.id.action_creationCollaboratorFragment_to_pictureGalleryFragment)
            setFragmentResultListener(requestKey) { _, bundle ->
                this.pictureUrl = bundle.getString(urlKey)

                Glide.with(requireView())
                    .load(pictureUrl)
                    .transform(CenterCrop(), RoundedCorners(15))
                    .into(binding.ivCollaboratorPhoto)
            }
        }
    }

    private fun isFieldsFilled(name: String, surname: String): Boolean {
        when {
            viewModel.companyPosition.value == -1 -> {
                showSnackbar(getString(R.string.choose_company))
                return false
            }
            TextUtils.isEmpty(name) -> {
                showSnackbar(getString(R.string.empty_name))
                return false
            }
            TextUtils.isEmpty(surname) -> {
                showSnackbar(getString(R.string.emty_surname))
                return false
            }
            pictureUrl == null -> {
                showSnackbar(getString(R.string.pick_picture))
                return false
            }
            else -> return true
        }
    }
}