package com.example.dmytroberezhnyi_vdatatesttask.fragment

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.fragment.app.viewModels
import com.example.dmytroberezhnyi_vdatatesttask.R
import com.example.dmytroberezhnyi_vdatatesttask.adapters.CompanySpinnerAdapter
import com.example.dmytroberezhnyi_vdatatesttask.data.entity.Collaborator
import com.example.dmytroberezhnyi_vdatatesttask.data.entity.Company
import com.example.dmytroberezhnyi_vdatatesttask.viewmodels.CreationCollaboratorViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.creation_collaborator_fragment.*

@AndroidEntryPoint
class CreationCollaboratorFragment : BaseFragment() {

    companion object {
        fun newInstance() = CreationCollaboratorFragment()
    }

    private val viewModel: CreationCollaboratorViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.creation_collaborator_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireMainActivity().hideToolbarPlusIcon()
        viewModel.companies.observe(viewLifecycleOwner, {
            it?.let {
                setupViews(it)
            }
        })
    }

    override fun getToolbarTitle(): String {
        return getString(R.string.new_collaborator)
    }

    private fun setupViews(companies: List<Company>) {
        var company: Company? = null
        val adapter =
            CompanySpinnerAdapter(requireContext(), R.layout.company_title_item, companies)

        tvWithSpinnerBehavior.onItemClickListener =
            AdapterView.OnItemClickListener { adapterView, _, position, _ ->
                company = adapterView?.getItemAtPosition(position) as Company
            }
        tvWithSpinnerBehavior.setAdapter(adapter)

        btnSave.setOnClickListener {
            val name = etName.text.toString()
            val surname = etSurname.text.toString()

            if (company == null) {
                showSnackbar("Choose company first :)")
            } else if (TextUtils.isEmpty(name)) {
                showSnackbar("Name can't be empty")
            } else if (TextUtils.isEmpty(surname)) {
                showSnackbar("Surname can't be empty")
            } else {
                val collaborator = Collaborator(
                    name = name,
                    surname = surname,
                    avatarUrl = "https://undark.org/wp-content/uploads/2020/02/GettyImages-1199242002-1-scaled.jpg"
                )
                viewModel.addCollaborator(collaborator, company!!)

                requireMainActivity()
                    .navController
                    .navigate(R.id.action_creationCollaboratorFragment_to_collaboratorFragment)
            }
        }
    }
}