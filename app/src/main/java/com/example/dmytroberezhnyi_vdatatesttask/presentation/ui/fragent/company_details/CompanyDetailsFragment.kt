package com.example.dmytroberezhnyi_vdatatesttask.presentation.ui.fragent.company_details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.dmytroberezhnyi_vdatatesttask.R
import com.example.dmytroberezhnyi_vdatatesttask.data.entity.Company
import com.example.dmytroberezhnyi_vdatatesttask.data.entity.toCollaboratorsWithCompany
import com.example.dmytroberezhnyi_vdatatesttask.databinding.CompanyDetalilsFragmentBinding
import com.example.dmytroberezhnyi_vdatatesttask.presentation.base.architecture.BaseFragment
import com.example.dmytroberezhnyi_vdatatesttask.presentation.ui.adapter.CollaboratorRecyclerAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CompanyDetailsFragment : BaseFragment<CompanyDetalilsFragmentBinding>() {

    companion object {
        const val companyKey = "companyKey"
    }

    override val layoutId: Int = R.layout.company_detalils_fragment

    private val viewModel: CompanyDetailsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclers()
    }

    private fun setupRecyclers() {
        val company: Company = arguments?.getParcelable(companyKey)!!
        val adapterWorkingCollab =
            CollaboratorRecyclerAdapter(CollaboratorRecyclerAdapter.CollaboratorWidthSize.WRAP_CONTENT)
        val adapterNotWorkingCollab =
            CollaboratorRecyclerAdapter(CollaboratorRecyclerAdapter.CollaboratorWidthSize.WRAP_CONTENT)

        binding.tvCompanyTitle.text = company.companyName

        viewModel.getCompanyWithCollaborators(company.companyId!!, true)
            .observe(viewLifecycleOwner) {
                it?.let {
                    binding.tvCollaboratorWorkingCount.text = it.size.toString()

                    if (it.isEmpty()) binding.rvCollaboratorsWorking.visibility = View.GONE
                    binding.rvCollaboratorsWorking.adapter = adapterWorkingCollab
                    adapterWorkingCollab.setItems(toCollaboratorsWithCompany(it, company))
                }
            }

        viewModel.getCompanyWithCollaborators(company.companyId!!, false)
            .observe(viewLifecycleOwner) {
                it?.let {
                    binding.tvCollaboratorNotWorkingAnymoreCount.text = it.size.toString()

                    if (it.isEmpty()) binding.rvCollaboratorsNotWorking.visibility = View.GONE
                    binding.rvCollaboratorsNotWorking.adapter = adapterNotWorkingCollab
                    adapterNotWorkingCollab.setItems(toCollaboratorsWithCompany(it, company))
                }
            }

        viewModel.getCollaboratorsTotalCount(company.companyId!!).observe(viewLifecycleOwner) {
            it?.let {
                binding.tvCollaboratorTotalCount.text = it.toString()
            }
        }
    }
}