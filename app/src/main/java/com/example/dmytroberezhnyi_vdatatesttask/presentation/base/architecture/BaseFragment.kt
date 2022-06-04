package com.example.dmytroberezhnyi_vdatatesttask.presentation.base.architecture

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.example.dmytroberezhnyi_vdatatesttask.presentation.ui.activity.MainActivity
import com.google.android.material.snackbar.Snackbar

abstract class BaseFragment<Binding : ViewDataBinding> : Fragment() {

    private val TAG = "BaseFragment"

    @get:LayoutRes
    abstract val layoutId: Int

    protected lateinit var binding: Binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireMainActivity().hideToolbarPlusIcon()
        binding.initView()
    }

    protected open fun Binding.initView() {}

    override fun onDestroyView() {
        super.onDestroyView()
        binding.unbind()
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