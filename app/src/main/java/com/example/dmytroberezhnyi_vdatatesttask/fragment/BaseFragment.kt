package com.example.dmytroberezhnyi_vdatatesttask.fragment

import android.os.Bundle
import android.view.View
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import com.example.dmytroberezhnyi_vdatatesttask.MainActivity
import com.google.android.material.snackbar.Snackbar
import timber.log.Timber

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

    protected fun navigate(@IdRes action : Int, args : Bundle? = null) {
        requireMainActivity().navController.navigate(action, args)
    }
}