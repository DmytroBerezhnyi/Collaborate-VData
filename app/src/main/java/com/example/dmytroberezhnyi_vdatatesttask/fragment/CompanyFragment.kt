package com.example.dmytroberezhnyi_vdatatesttask.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.dmytroberezhnyi_vdatatesttask.R
import com.example.dmytroberezhnyi_vdatatesttask.adapters.CompanyRecyclerAdapter
import com.example.dmytroberezhnyi_vdatatesttask.adapters.CompanyViewHolder.CompanySize
import com.example.dmytroberezhnyi_vdatatesttask.adapters.CompanyViewHolder.OnCompanyItemPressedListener
import com.example.dmytroberezhnyi_vdatatesttask.data.entity.Company
import com.example.dmytroberezhnyi_vdatatesttask.ui.MainActivity
import com.example.dmytroberezhnyi_vdatatesttask.viewmodels.CompanyViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.company_fragment.*


@AndroidEntryPoint
class CompanyFragment : BaseFragment(), MainActivity.OnAddIconClickedListener,
    OnCompanyItemPressedListener {

    companion object {
        fun newInstance() = CompanyFragment()
    }

    private val viewModel: CompanyViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.company_fragment, container, false)
    }

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
        val adapter = CompanyRecyclerAdapter(CompanySize.NORMAL, this)
        rvCompanies.adapter = adapter
        viewModel.companies.observe(viewLifecycleOwner, {
            it?.let {
                adapter.setItems(it)
            }
        })
    }
}