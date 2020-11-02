package com.example.dmytroberezhnyi_vdatatesttask.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import com.example.dmytroberezhnyi_vdatatesttask.MainActivity
import com.example.dmytroberezhnyi_vdatatesttask.R
import com.example.dmytroberezhnyi_vdatatesttask.adapters.CompanyRecyclerAdapter
import com.example.dmytroberezhnyi_vdatatesttask.adapters.CompanyViewHolder.CompanySize
import com.example.dmytroberezhnyi_vdatatesttask.adapters.CompanyViewHolder.OnCompanyItemLongPressedListener
import com.example.dmytroberezhnyi_vdatatesttask.data.entity.Company
import com.example.dmytroberezhnyi_vdatatesttask.viewmodels.CompanyViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.company_fragment.*


@AndroidEntryPoint
class CompanyFragment : BaseFragment(), MainActivity.OnAddIconClickedListener,
    OnCompanyItemLongPressedListener {

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
        val builder: AlertDialog.Builder = AlertDialog.Builder(requireContext())

        builder.setTitle("Do you want to delete ${company.companyName}")
        builder.setMessage("Are you sure?")

        builder.setPositiveButton("YES") { dialog, which ->
            viewModel.deleteCompany(company)
            dialog.dismiss()
        }

        builder.setNegativeButton("NO") { dialog, which ->
            dialog.dismiss()
        }

        val alert: AlertDialog = builder.create()
        alert.show()
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