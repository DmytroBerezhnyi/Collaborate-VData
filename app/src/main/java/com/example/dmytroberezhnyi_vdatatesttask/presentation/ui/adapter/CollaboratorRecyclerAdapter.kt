package com.example.dmytroberezhnyi_vdatatesttask.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.example.dmytroberezhnyi_vdatatesttask.R
import com.example.dmytroberezhnyi_vdatatesttask.data.entity.Collaborator
import com.example.dmytroberezhnyi_vdatatesttask.data.entity.CollaboratorWithCompanies
import com.example.dmytroberezhnyi_vdatatesttask.presentation.ui.adapter.CollaboratorViewHolder.CollaboratorWidthSize
import com.example.dmytroberezhnyi_vdatatesttask.presentation.ui.adapter.CompanyViewHolder.CompanySize
import kotlinx.android.synthetic.main.collaborator_item.view.*
import kotlinx.android.synthetic.main.company_fragment.view.rvCompanies
import java.util.*

class CollaboratorRecyclerAdapter(
    private val collaboratorSize: CollaboratorWidthSize,
    private val listener: CollaboratorViewHolder.OnCollaboratorItemClickedListener? = null
) :
    RecyclerView.Adapter<CollaboratorViewHolder>() {

    private val items = ArrayList<CollaboratorWithCompanies>()

    fun setItems(items: List<CollaboratorWithCompanies>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CollaboratorViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.collaborator_item, parent, false)
        return CollaboratorViewHolder(view, collaboratorSize, listener)
    }

    override fun onBindViewHolder(holder: CollaboratorViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }
}

class CollaboratorViewHolder(
    itemView: View,
    private val collaboratorSize: CollaboratorWidthSize,
    listener: OnCollaboratorItemClickedListener?
) : RecyclerView.ViewHolder(itemView) {

    lateinit var collaborator: Collaborator

    init {
        itemView.setOnClickListener {
            listener?.let {
                listener.onCollaboratorClicked(collaborator)
            }
        }
    }

    fun bind(collaboratorWithCompanies: CollaboratorWithCompanies) {
        this.collaborator = collaboratorWithCompanies.collaborator
        if (collaboratorSize == CollaboratorWidthSize.MUCH_PARENT) {
            itemView.cvCollaborator.layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT
        } else if (collaboratorSize == CollaboratorWidthSize.WRAP_CONTENT) {
            itemView.cvCollaborator.layoutParams.width = ViewGroup.LayoutParams.WRAP_CONTENT
        }
        setUpAdapter(collaboratorWithCompanies)
        val collaborator = collaboratorWithCompanies.collaborator
        val name = "${collaborator.name} ${collaborator.surname}"
        itemView.tvNameAndSurname.text = name

        Glide.with(itemView)
            .load(collaborator.avatarUrl)
            .transform(CircleCrop())
            .into(itemView.ivPhoto)
    }

    private fun setUpAdapter(collaboratorWithCompanies: CollaboratorWithCompanies) {
        val adapter = CompanyRecyclerAdapter(CompanySize.SMALL, null)
        itemView.rvCompanies.adapter = adapter
        adapter.setItems(collaboratorWithCompanies.companies)
    }

    interface OnCollaboratorItemClickedListener {
        fun onCollaboratorClicked(collaborator: Collaborator)
    }

    enum class CollaboratorWidthSize {
        WRAP_CONTENT,
        MUCH_PARENT
    }
}