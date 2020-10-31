package com.example.dmytroberezhnyi_vdatatesttask.fragment

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.example.dmytroberezhnyi_vdatatesttask.R
import com.example.dmytroberezhnyi_vdatatesttask.viewmodels.AddCompanyViewModel

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
                .setPositiveButton("OK", object : DialogInterface.OnClickListener {
                    override fun onClick(dialog: DialogInterface?, which: Int) {
                        val companyName = input.text.toString()
                        Toast.makeText(it, companyName, Toast.LENGTH_SHORT).show()
                    }
                })
                .setNegativeButton("Cancel", object : DialogInterface.OnClickListener {
                    override fun onClick(dialog: DialogInterface?, which: Int) {
                        dialog?.cancel()
                    }
                })
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }
}