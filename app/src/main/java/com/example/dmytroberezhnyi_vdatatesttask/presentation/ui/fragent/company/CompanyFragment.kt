package com.example.dmytroberezhnyi_vdatatesttask.presentation.ui.fragent.company

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.ComposeView
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.dmytroberezhnyi_vdatatesttask.R
import com.example.dmytroberezhnyi_vdatatesttask.data.entity.Company
import com.example.dmytroberezhnyi_vdatatesttask.presentation.base.architecture.BaseFragment
import com.example.dmytroberezhnyi_vdatatesttask.presentation.ui.activity.MainActivity
import com.example.dmytroberezhnyi_vdatatesttask.presentation.ui.fragent.add_company.AddCompanyFragment
import com.example.dmytroberezhnyi_vdatatesttask.presentation.ui.fragent.company_details.CompanyDetailsFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CompanyFragment : BaseFragment(), MainActivity.OnAddIconClickedListener {

    companion object {
        fun newInstance() = CompanyFragment()
    }

    private val viewModel: CompanyViewModel by viewModels()

    private val onCompanyItemClicked: (company: Company) -> Unit = {
        val bundle = bundleOf(Pair(CompanyDetailsFragment.companyKey, it))
        findNavController().navigate(R.id.action_companyFragment_to_companyDetailsFragment, bundle)
    }

    private val onCompanyItemLongPressed: (company: Company) -> Unit = {
        val title = "Do you want to delete ${it.companyName}"
        val message = "Are you sure?"
        val actionYes: () -> Unit = { viewModel.deleteCompany(it) }
        showDialog(title, message, actionYes)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                MaterialTheme {
                    Companies(
                        companyList = viewModel.companies.observeAsState(),
                        onCompanyItemClicked,
                        onCompanyItemLongPressed
                    )
                }
            }
        }
        //return inflater.inflate(R.layout.company_fragment, container, false)
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

    private fun setUpRecycler() {
        /*val adapter = CompanyRecyclerAdapter(CompanySize.NORMAL, this)
        rvCompanies.adapter = adapter
        viewModel.companies.observe(viewLifecycleOwner, {
            it?.let {
                adapter.setItems(it)
            }
        })*/
    }
}