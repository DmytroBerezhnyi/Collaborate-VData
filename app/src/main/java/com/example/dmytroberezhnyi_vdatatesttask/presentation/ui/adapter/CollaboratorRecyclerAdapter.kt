package com.example.dmytroberezhnyi_vdatatesttask.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.example.dmytroberezhnyi_vdatatesttask.data.entity.Collaborator
import com.example.dmytroberezhnyi_vdatatesttask.data.entity.CollaboratorWithCompanies
import com.example.dmytroberezhnyi_vdatatesttask.databinding.CollaboratorItemBinding

class CollaboratorRecyclerAdapter(
    private val collaboratorSize: CollaboratorWidthSize,
    private val listener: OnCollaboratorItemClickedListener? = null
) :
    RecyclerView.Adapter<CollaboratorRecyclerAdapter.CollaboratorViewHolder>() {

    private val items = ArrayList<CollaboratorWithCompanies>()

    fun setItems(items: List<CollaboratorWithCompanies>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CollaboratorViewHolder {
        val binding =
            CollaboratorItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CollaboratorViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CollaboratorViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    interface OnCollaboratorItemClickedListener {
        fun onCollaboratorClicked(collaborator: Collaborator)
    }

    enum class CollaboratorWidthSize {
        WRAP_CONTENT,
        MUCH_PARENT
    }

    inner class CollaboratorViewHolder(val binding: CollaboratorItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        lateinit var collaborator: Collaborator

        fun bind(collaboratorWithCompanies: CollaboratorWithCompanies) {
            this.collaborator = collaboratorWithCompanies.collaborator

            binding.root.setOnClickListener {
                listener?.let {
                    listener.onCollaboratorClicked(collaborator)
                }
            }

            if (collaboratorSize == CollaboratorWidthSize.MUCH_PARENT) {
                binding.cvCollaborator.layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT
            } else if (collaboratorSize == CollaboratorWidthSize.WRAP_CONTENT) {
                binding.cvCollaborator.layoutParams.width = ViewGroup.LayoutParams.WRAP_CONTENT
            }
            setUpAdapter(collaboratorWithCompanies)
            val collaborator = collaboratorWithCompanies.collaborator
            val name = "${collaborator.name} ${collaborator.surname}"
            binding.tvNameAndSurname.text = name

            Glide.with(itemView)
                .load(collaborator.avatarUrl)
                .transform(CircleCrop())
                .into(binding.ivPhoto)
        }

        private fun setUpAdapter(collaboratorWithCompanies: CollaboratorWithCompanies) {
            val adapter = CompanyRecyclerAdapter(CompanyRecyclerAdapter.CompanySize.SMALL, null)
            binding.rvCompanies.adapter = adapter
            adapter.setItems(collaboratorWithCompanies.companies)
        }
    }
}