package com.example.dmytroberezhnyi_vdatatesttask.presentation.base.architecture

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.example.dmytroberezhnyi_vdatatesttask.presentation.ui.activity.MainActivity
import com.google.android.material.snackbar.Snackbar

abstract class BaseFragment : Fragment() {

    private val TAG = "BaseFragment"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireMainActivity().hideToolbarPlusIcon()
    }

    protected fun showSnackbar(message: String) {
        Snackbar.make(requireView(), message, Snackbar.LENGTH_SHORT).show()
    }

    //Single activity pattern
    protected fun requireMainActivity(): MainActivity {
        return requireActivity() as MainActivity
    }

    protected fun showDialog(
        title: String,
        message: String,
        actionYes: () -> Unit,
        actionNo: (() -> Unit)? = null
    ) {
        val builder: AlertDialog.Builder = AlertDialog.Builder(requireContext())

        builder.setTitle(title)
        builder.setMessage(message)

        builder.setPositiveButton("YES") { dialog, _ ->
            actionYes.invoke()
            dialog.dismiss()
        }

        builder.setNegativeButton("NO") { dialog, _ ->
            actionNo?.invoke()
            dialog.dismiss()
        }

        val alert: AlertDialog = builder.create()
        alert.show()
    }
}