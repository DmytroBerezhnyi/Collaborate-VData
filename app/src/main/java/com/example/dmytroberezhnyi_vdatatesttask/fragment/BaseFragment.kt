package com.example.dmytroberezhnyi_vdatatesttask.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import com.example.dmytroberezhnyi_vdatatesttask.MainActivity

abstract class BaseFragment : Fragment() {

    private val TAG = "BaseFragment"

    abstract fun getToolbarTitle(): String

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        invalidateToolbarTitle()
    }

    private fun invalidateToolbarTitle() {
        if (activity != null) {
            requireMainActivity().title = getToolbarTitle()
        } else {
            Log.e(TAG, "getActivity(); returns null. Couldn't call invalidateToolbarTitle();")
        }
    }

    //Single activity pattern
    protected fun requireMainActivity(): MainActivity {
        return requireActivity() as MainActivity
    }
}