package com.example.dmytroberezhnyi_vdatatesttask.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.dmytroberezhnyi_vdatatesttask.MainActivity
import com.example.dmytroberezhnyi_vdatatesttask.R
import com.example.dmytroberezhnyi_vdatatesttask.adapters.CollaboratorRecyclerAdapter
import com.example.dmytroberezhnyi_vdatatesttask.viewmodels.CollaboratorViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.collaborator_fragment.*

@AndroidEntryPoint
class CollaboratorFragment : BaseFragment(), MainActivity.OnAddIconClickedListener {

    companion object {
        fun newInstance() = CollaboratorFragment()
    }

    private val viewModel: CollaboratorViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.collaborator_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireMainActivity().showToolbarPlusIcon()
        requireMainActivity().setOnIconClickedListener(this)

        val adapter = CollaboratorRecyclerAdapter()
        rvCollaborators.adapter = adapter

        viewModel.collaboratorsWithCompanies.observe(viewLifecycleOwner, {
            it?.let {
                adapter.setItems(it)
            }
        })
    }

    override fun onAddButtonClicked() {
        navigate(R.id.action_collaboratorFragment_to_creationCollaboratorFragment)
    }
}