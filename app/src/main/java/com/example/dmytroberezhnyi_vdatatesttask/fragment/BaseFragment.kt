package com.example.dmytroberezhnyi_vdatatesttask.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.dmytroberezhnyi_vdatatesttask.MainActivity
import com.google.android.material.snackbar.Snackbar
import timber.log.Timber

abstract class BaseFragment : Fragment() {

    private val TAG = "BaseFragment"

    abstract fun getToolbarTitle(): String

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        invalidateToolbarTitle()
    }

    protected fun showSnackbar(message: String) {
        Snackbar.make(requireView(), message, Snackbar.LENGTH_SHORT).show()
    }

    private fun invalidateToolbarTitle() {
        if (activity != null) {
            requireMainActivity().title = getToolbarTitle()
        } else {
            Timber.e("getActivity() returns null. Couldn't call invalidateToolbarTitle()")
        }
    }

    //Single activity pattern
    protected fun requireMainActivity(): MainActivity {
        return requireActivity() as MainActivity
    }
}