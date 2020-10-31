package com.example.dmytroberezhnyi_vdatatesttask.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.dmytroberezhnyi_vdatatesttask.R
import com.example.dmytroberezhnyi_vdatatesttask.adapters.CompanySpinnerAdapter
import com.example.dmytroberezhnyi_vdatatesttask.data.entity.Company
import com.example.dmytroberezhnyi_vdatatesttask.viewmodels.CreationCollaboratorViewModel
import kotlinx.android.synthetic.main.creation_collaborator_fragment.*

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
        setupCompanySpinner()
    }

    override fun getToolbarTitle(): String {
        return getString(R.string.new_collaborator)
    }

    private fun setupCompanySpinner() {
        val adapter = CompanySpinnerAdapter(
            requireContext(),
            R.layout.company_title_item,
            viewModel.companies.value!!
        )

        tvWithSpinnerBehavior.onItemClickListener =
            AdapterView.OnItemClickListener { adapterView, view, position, id ->
                val company = adapterView?.getItemAtPosition(position) as Company
                Toast.makeText(requireContext(), company.companyName, Toast.LENGTH_SHORT).show()
            }
        tvWithSpinnerBehavior.setAdapter(adapter)
    }
}