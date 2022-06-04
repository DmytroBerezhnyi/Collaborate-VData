package com.example.dmytroberezhnyi_vdatatesttask.presentation.ui.fragent.collaborator

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.dmytroberezhnyi_vdatatesttask.R
import com.example.dmytroberezhnyi_vdatatesttask.data.entity.Collaborator
import com.example.dmytroberezhnyi_vdatatesttask.databinding.CollaboratorFragmentBinding
import com.example.dmytroberezhnyi_vdatatesttask.presentation.base.architecture.BaseFragment
import com.example.dmytroberezhnyi_vdatatesttask.presentation.ui.activity.MainActivity.OnAddIconClickedListener
import com.example.dmytroberezhnyi_vdatatesttask.presentation.ui.adapter.CollaboratorRecyclerAdapter
import com.example.dmytroberezhnyi_vdatatesttask.presentation.ui.fragent.edit_collaborator.EditCollaboratorFragment.Companion.collaboratorKey
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CollaboratorFragment : BaseFragment<CollaboratorFragmentBinding>(), OnAddIconClickedListener,
    CollaboratorRecyclerAdapter.OnCollaboratorItemClickedListener {

    private val viewModel: CollaboratorViewModel by viewModels()

    override val layoutId: Int
        get() = R.layout.collaborator_fragment

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireMainActivity().showToolbarPlusIcon()
        requireMainActivity().setOnIconClickedListener(this)

        val adapter = CollaboratorRecyclerAdapter(CollaboratorRecyclerAdapter.CollaboratorWidthSize.MUCH_PARENT, this)
        binding.rvCollaborators.adapter = adapter

        viewModel.collaboratorsWithCompanies.observe(viewLifecycleOwner) {
            it?.let {
                adapter.setItems(it)
            }
        }
    }

    override fun onAddButtonClicked() {
        findNavController().navigate(R.id.action_collaboratorFragment_to_creationCollaboratorFragment)
    }

    override fun onCollaboratorClicked(collaborator: Collaborator) {
        val bundle = bundleOf(Pair(collaboratorKey, collaborator))
        findNavController()
            .navigate(R.id.action_collaboratorFragment_to_editCollaboratorFragment, bundle)
    }
}