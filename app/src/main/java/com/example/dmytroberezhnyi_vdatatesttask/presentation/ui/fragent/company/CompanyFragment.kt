package com.example.dmytroberezhnyi_vdatatesttask.presentation.ui.fragent.company

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.dmytroberezhnyi_vdatatesttask.R
import com.example.dmytroberezhnyi_vdatatesttask.data.entity.Company
import com.example.dmytroberezhnyi_vdatatesttask.databinding.CompanyFragmentBinding
import com.example.dmytroberezhnyi_vdatatesttask.presentation.base.architecture.BaseFragment
import com.example.dmytroberezhnyi_vdatatesttask.presentation.ui.activity.MainActivity
import com.example.dmytroberezhnyi_vdatatesttask.presentation.ui.adapter.CompanyRecyclerAdapter
import com.example.dmytroberezhnyi_vdatatesttask.presentation.ui.fragent.add_company.AddCompanyFragment
import com.example.dmytroberezhnyi_vdatatesttask.presentation.ui.fragent.company_details.CompanyDetailsFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CompanyFragment : BaseFragment<CompanyFragmentBinding>(), MainActivity.OnAddIconClickedListener,
    CompanyRecyclerAdapter.OnCompanyItemPressedListener {

    companion object {
        fun newInstance() = CompanyFragment()
    }

    override val layoutId: Int
        get() = R.layout.company_fragment

    private val viewModel: CompanyViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireMainActivity().showToolbarPlusIcon()
        requireMainActivity().setOnIconClickedListener(this)
        setUpRecycler()
    }

    override fun onAddButtonClicked() {
        AddCompanyFragment.newInstance().show(activity?.supportFragmentManager!!, "simple_dialog")
    }

    override fun onCompanyItemLongPressed(company: Company) {
        val title = "Do you want to delete ${company.companyName}"
        val message = "Are you sure?"
        val actionYes: () -> Unit = { viewModel.deleteCompany(company) }
        showDialog(title, message, actionYes)
    }

    override fun onCompanyItemClicked(company: Company) {
        val bundle = bundleOf(Pair(CompanyDetailsFragment.companyKey, company))
        findNavController().navigate(R.id.action_companyFragment_to_companyDetailsFragment, bundle)
    }

    private fun setUpRecycler() {
        val adapter = CompanyRecyclerAdapter(CompanyRecyclerAdapter.CompanySize.NORMAL, this)
        binding.rvCompanies.adapter = adapter
        viewModel.companies.observe(viewLifecycleOwner) {
            it?.let {
                adapter.setItems(it)
            }
        }
    }
}