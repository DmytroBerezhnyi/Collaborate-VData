package com.example.dmytroberezhnyi_vdatatesttask.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.dmytroberezhnyi_vdatatesttask.MainActivity
import com.example.dmytroberezhnyi_vdatatesttask.R
import com.example.dmytroberezhnyi_vdatatesttask.adapters.CompanyRecyclerAdapter
import com.example.dmytroberezhnyi_vdatatesttask.viewmodels.CompanyViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.company_fragment.*


@AndroidEntryPoint
class CompanyFragment : BaseFragment(), MainActivity.OnAddIconClickedListener {

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

    override fun getToolbarTitle(): String {
        return getString(R.string.company)
    }

    override fun onAddButtonClicked() {
        AddCompanyFragment.newInstance().show(activity?.supportFragmentManager!!, "simple_dialog")
    }

    private fun setUpRecycler() {
        val adapter = CompanyRecyclerAdapter()
        rvCompanies.adapter = adapter
        viewModel.companies.observe(viewLifecycleOwner, {
            it?.let {
                adapter.setItems(it)
            }
        })
    }
}