package com.example.dmytroberezhnyi_vdatatesttask.presentation.ui.fragent.add_company

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.example.dmytroberezhnyi_vdatatesttask.R
import com.example.dmytroberezhnyi_vdatatesttask.data.entity.Company
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddCompanyFragment : DialogFragment() {

    companion object {
        fun newInstance() = AddCompanyFragment()
    }

    private val viewModel: AddCompanyViewModel by viewModels()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            val input = EditText(it)
            builder.setTitle(getString(R.string.add_company))
                .setCancelable(true)
                .setView(input)
                .setPositiveButton("OK"
                ) { dialog, which ->
                    val companyName = input.text.toString()
                    val company = Company(companyName = companyName)
                    viewModel.addCompany(company)
                }
                .setNegativeButton("Cancel"
                ) { dialog, which -> dialog?.cancel() }
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}