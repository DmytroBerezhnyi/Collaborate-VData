package com.example.dmytroberezhnyi_vdatatesttask.presentation.ui.fragent.edit_collaborator

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
import com.example.dmytroberezhnyi_vdatatesttask.databinding.EditCollaboratorFragmentBinding
import com.example.dmytroberezhnyi_vdatatesttask.presentation.base.architecture.BaseFragment
import com.example.dmytroberezhnyi_vdatatesttask.presentation.base.extension.getText
import com.example.dmytroberezhnyi_vdatatesttask.presentation.base.extension.setText
import com.example.dmytroberezhnyi_vdatatesttask.presentation.ui.adapter.CompanyRecyclerAdapter
import com.example.dmytroberezhnyi_vdatatesttask.presentation.ui.adapter.CompanySpinnerAdapter
import com.example.dmytroberezhnyi_vdatatesttask.presentation.ui.fragent.picture_gallery.PictureGalleryFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EditCollaboratorFragment : BaseFragment<EditCollaboratorFragmentBinding>(),
    CompanyRecyclerAdapter.OnCompanyItemPressedListener {

    companion object {

        const val collaboratorKey = "collaboratorKey"

        fun newInstance() = EditCollaboratorFragment()
    }

    override val layoutId: Int
        get() = R.layout.edit_collaborator_fragment

    private val viewModel: EditCollaboratorViewModel by viewModels()

    private lateinit var collaborator: Collaborator

    private lateinit var pictureUrl: String

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
    }

    private fun setupViews() {
        collaborator = arguments?.getParcelable(collaboratorKey)!!
        pictureUrl = collaborator.avatarUrl
        val companySpinnerAdapter = CompanySpinnerAdapter(
            requireContext(),
            R.layout.company_title_item,
            ArrayList<Company>()
        )
        binding.tilName.setText(collaborator.name)
        binding.tilSurname.setText(collaborator.surname)

        setupCollaboratorPhoto()

        setupRecycler(companySpinnerAdapter)

        setupSpinner(companySpinnerAdapter)


        binding.btnSave.setOnClickListener {
            val name = binding.tilName.getText()
            val surname = binding.tilSurname.getText()

            if (TextUtils.isEmpty(name)) {
                showSnackbar(getString(R.string.empty_name))
            } else if (TextUtils.isEmpty(surname)) {
                showSnackbar(getString(R.string.emty_surname))
            } else {
                val updatedCollaborator =
                    Collaborator(collaborator.collaboratorId, name, surname, pictureUrl)
                viewModel.updateCollaborator(updatedCollaborator)
                findNavController().navigate(R.id.action_editCollaboratorFragment_to_collaboratorFragment)
            }
        }
    }

    private fun setupCollaboratorPhoto() {
        Glide.with(requireView())
            .load(pictureUrl)
            .transform(CenterCrop(), RoundedCorners(15))
            .into(binding.ivCollaboratorPhoto)

        binding.ivCollaboratorPhoto.setOnClickListener {
            findNavController().navigate(R.id.action_editCollaboratorFragment_to_pictureGalleryFragment)
            setFragmentResultListener(PictureGalleryFragment.requestKey) { _, bundle ->
                pictureUrl = bundle.getString(PictureGalleryFragment.urlKey)!!

                Glide.with(requireView())
                    .load(pictureUrl)
                    .transform(CenterCrop(), RoundedCorners(15))
                    .into(binding.ivCollaboratorPhoto)
            }
        }
    }

    private fun setupSpinner(companySpinnerAdapter: CompanySpinnerAdapter) {
        binding.tvWithSpinnerBehaviorNewWork.onItemClickListener =
            AdapterView.OnItemClickListener { adapterView, _, position, _ ->
                val company = adapterView?.getItemAtPosition(position) as Company
                val title =
                    "Do you want to add ${company.companyName} for ${collaborator.name} ${collaborator.surname}"
                val message = "Are you sure?"
                val actionYes: () -> Unit = {
                    viewModel.addCompanyForCollaborator(
                        collaborator.collaboratorId!!,
                        company.companyId!!
                    )
                }
                showDialog(title, message, actionYes)
            }

        binding.tvWithSpinnerBehaviorNewWork.setAdapter(companySpinnerAdapter)
    }

    private fun setupRecycler(companySpinnerAdapter: CompanySpinnerAdapter) {
        val workedCompanyAdapter = CompanyRecyclerAdapter(CompanyRecyclerAdapter.CompanySize.SMALL)
        binding.rvWorkedCompanies.adapter = workedCompanyAdapter

        val currentCompanyAdapter = CompanyRecyclerAdapter(CompanyRecyclerAdapter.CompanySize.SMALL, this)
        binding.rvCurrentCompanies.adapter = currentCompanyAdapter

        viewModel.getCompanies(collaborator.collaboratorId!!, false).observe(viewLifecycleOwner) {
            workedCompanyAdapter.setItems(it)
        }

        viewModel.getCompanies(collaborator.collaboratorId!!, true)
            .observe(viewLifecycleOwner) { currentCompanies ->
                currentCompanyAdapter.setItems(currentCompanies)
                viewModel.getAllAvailableCompaniesForCollab()
                    .observe(viewLifecycleOwner) { allCompanies ->
                        allCompanies?.let {
                            val items = allCompanies.minus(currentCompanies.toSet())
                            companySpinnerAdapter.setItems(items)
                        }
                    }
            }
    }

    override fun onCompanyItemLongPressed(company: Company) {
        val title =
            "Do you want to fire ${collaborator.name} ${collaborator.surname} from ${company.companyName}"
        val message = "Are you sure?"
        val actionYes: () -> Unit = {
            viewModel.fireCollaboratorFromCompany(
                collaborator.collaboratorId!!,
                company.companyId!!
            )
        }
        showDialog(title, message, actionYes)
    }

    override fun onCompanyItemClicked(company: Company) {
        //none
    }
}