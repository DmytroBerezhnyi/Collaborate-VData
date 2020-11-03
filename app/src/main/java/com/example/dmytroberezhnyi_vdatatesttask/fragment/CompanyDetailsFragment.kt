package com.example.dmytroberezhnyi_vdatatesttask.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.dmytroberezhnyi_vdatatesttask.R
import com.example.dmytroberezhnyi_vdatatesttask.adapters.CollaboratorRecyclerAdapter
import com.example.dmytroberezhnyi_vdatatesttask.adapters.CollaboratorViewHolder.CollaboratorWidthSize
import com.example.dmytroberezhnyi_vdatatesttask.data.entity.Company
import com.example.dmytroberezhnyi_vdatatesttask.data.entity.toCollaboratorsWithCompany
import com.example.dmytroberezhnyi_vdatatesttask.viewmodels.CompanyDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.company_detalils_fragment.*

@AndroidEntryPoint
class CompanyDetailsFragment : BaseFragment() {

    companion object {

        const val companyKey = "companyKey"

        fun newInstance() = CompanyDetailsFragment()
    }

    private val viewModel: CompanyDetailsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.company_detalils_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclers()
    }

    private fun setupRecyclers() {
        val company: Company = arguments?.getParcelable(companyKey)!!
        val adapterWorkingCollab = CollaboratorRecyclerAdapter(CollaboratorWidthSize.WRAP_CONTENT)
        val adapterNotWorkingCollab =
            CollaboratorRecyclerAdapter(CollaboratorWidthSize.WRAP_CONTENT)

        tvCompanyTitle.text = company.companyName

        viewModel.getCompanyWithCollaborators(company.companyId!!, true)
            .observe(viewLifecycleOwner, {
                it?.let {
                    tvCollaboratorWorkingCount.text = it.size.toString()

                    if (it.isEmpty()) rvCollaboratorsWorking.visibility = View.GONE
                    rvCollaboratorsWorking.adapter = adapterWorkingCollab
                    adapterWorkingCollab.setItems(toCollaboratorsWithCompany(it, company))
                }
            })

        viewModel.getCompanyWithCollaborators(company.companyId!!, false)
            .observe(viewLifecycleOwner, {
                it?.let {
                    tvCollaboratorNotWorkingAnymoreCount.text = it.size.toString()

                    if (it.isEmpty()) rvCollaboratorsNotWorking.visibility = View.GONE
                    rvCollaboratorsNotWorking.adapter = adapterNotWorkingCollab
                    adapterNotWorkingCollab.setItems(toCollaboratorsWithCompany(it, company))
                }
            })

        viewModel.getCollaboratorsTotalCount(company.companyId!!).observe(viewLifecycleOwner, {
            it?.let {
                tvCollaboratorTotalCount.text = it.toString()
            }
        })
    }
}