package com.example.dmytroberezhnyi_vdatatesttask.fragment

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.dmytroberezhnyi_vdatatesttask.R
import com.example.dmytroberezhnyi_vdatatesttask.adapters.CompanyRecyclerAdapter
import com.example.dmytroberezhnyi_vdatatesttask.adapters.CompanySpinnerAdapter
import com.example.dmytroberezhnyi_vdatatesttask.adapters.CompanyViewHolder.CompanySize
import com.example.dmytroberezhnyi_vdatatesttask.adapters.CompanyViewHolder.OnCompanyItemPressedListener
import com.example.dmytroberezhnyi_vdatatesttask.data.entity.Collaborator
import com.example.dmytroberezhnyi_vdatatesttask.data.entity.Company
import com.example.dmytroberezhnyi_vdatatesttask.viewmodels.EditCollaboratorViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.creation_collaborator_fragment.*
import kotlinx.android.synthetic.main.edit_collaborator_fragment.*
import kotlinx.android.synthetic.main.edit_collaborator_fragment.btnSave
import kotlinx.android.synthetic.main.edit_collaborator_fragment.etName
import kotlinx.android.synthetic.main.edit_collaborator_fragment.etSurname
import kotlinx.android.synthetic.main.edit_collaborator_fragment.ivCollaboratorPhoto
import java.util.*

@AndroidEntryPoint
class EditCollaboratorFragment : BaseFragment(), OnCompanyItemPressedListener {

    companion object {

        const val collaboratorKey = "collaboratorKey"

        fun newInstance() = EditCollaboratorFragment()
    }

    private val viewModel: EditCollaboratorViewModel by viewModels()

    private lateinit var collaborator: Collaborator

    private lateinit var pictureUrl: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.edit_collaborator_fragment, container, false)
    }

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
        etName.setText(collaborator.name)
        etSurname.setText(collaborator.surname)

        setupCollaboratorPhoto()

        setupRecycler(companySpinnerAdapter)

        setupSpinner(companySpinnerAdapter)


        btnSave.setOnClickListener {
            val name = etName.text.toString()
            val surname = etSurname.text.toString()

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
            .into(ivCollaboratorPhoto)

        ivCollaboratorPhoto.setOnClickListener {
            findNavController().navigate(R.id.action_editCollaboratorFragment_to_pictureGalleryFragment)
            setFragmentResultListener(PictureGalleryFragment.requestKey) { _, bundle ->
                pictureUrl = bundle.getString(PictureGalleryFragment.urlKey)!!

                Glide.with(requireView())
                    .load(pictureUrl)
                    .transform(CenterCrop(), RoundedCorners(15))
                    .into(ivCollaboratorPhoto)
            }
        }
    }

    private fun setupSpinner(companySpinnerAdapter: CompanySpinnerAdapter) {
        tvWithSpinnerBehaviorNewWork.onItemClickListener =
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

        tvWithSpinnerBehaviorNewWork.setAdapter(companySpinnerAdapter)
    }

    private fun setupRecycler(companySpinnerAdapter: CompanySpinnerAdapter) {
        val workedCompanyAdapter = CompanyRecyclerAdapter(CompanySize.SMALL)
        rvWorkedCompanies.adapter = workedCompanyAdapter

        val currentCompanyAdapter = CompanyRecyclerAdapter(CompanySize.SMALL, this)
        rvCurrentCompanies.adapter = currentCompanyAdapter

        viewModel.getCompanies(collaborator.collaboratorId!!, false).observe(viewLifecycleOwner, {
            workedCompanyAdapter.setItems(it)

        })

        viewModel.getCompanies(collaborator.collaboratorId!!, true)
            .observe(viewLifecycleOwner, { currentCompanies ->
                currentCompanyAdapter.setItems(currentCompanies)
                viewModel.getAllAvailableCompaniesForCollab()
                    .observe(viewLifecycleOwner, { allCompanies ->
                        allCompanies?.let {
                            val items = allCompanies.minus(currentCompanies)
                            companySpinnerAdapter.setItems(items)
                        }
                    })
            })
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