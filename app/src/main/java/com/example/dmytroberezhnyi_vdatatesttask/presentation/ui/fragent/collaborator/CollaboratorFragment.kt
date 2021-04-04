package com.example.dmytroberezhnyi_vdatatesttask.presentation.ui.fragent.collaborator

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.dmytroberezhnyi_vdatatesttask.R
import com.example.dmytroberezhnyi_vdatatesttask.data.entity.Collaborator
import com.example.dmytroberezhnyi_vdatatesttask.presentation.base.architecture.BaseFragment
import com.example.dmytroberezhnyi_vdatatesttask.presentation.ui.activity.MainActivity.OnAddIconClickedListener
import com.example.dmytroberezhnyi_vdatatesttask.presentation.ui.adapter.CollaboratorRecyclerAdapter
import com.example.dmytroberezhnyi_vdatatesttask.presentation.ui.adapter.CollaboratorViewHolder.CollaboratorWidthSize
import com.example.dmytroberezhnyi_vdatatesttask.presentation.ui.adapter.CollaboratorViewHolder.OnCollaboratorItemClickedListener
import com.example.dmytroberezhnyi_vdatatesttask.presentation.ui.fragent.edit_collaborator.EditCollaboratorFragment.Companion.collaboratorKey
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.collaborator_fragment.*

@AndroidEntryPoint
class CollaboratorFragment : BaseFragment(), OnAddIconClickedListener,
    OnCollaboratorItemClickedListener {

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

        val adapter = CollaboratorRecyclerAdapter(CollaboratorWidthSize.MUCH_PARENT, this)
        rvCollaborators.adapter = adapter

        viewModel.collaboratorsWithCompanies.observe(viewLifecycleOwner, {
            it?.let {
                adapter.setItems(it)
            }
        })
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