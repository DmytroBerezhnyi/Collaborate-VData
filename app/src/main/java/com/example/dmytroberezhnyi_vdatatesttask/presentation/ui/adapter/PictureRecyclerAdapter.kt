package com.example.dmytroberezhnyi_vdatatesttask.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.dmytroberezhnyi_vdatatesttask.R
import com.example.dmytroberezhnyi_vdatatesttask.data.pojo.Item
import com.example.dmytroberezhnyi_vdatatesttask.presentation.ui.adapter.PictureViewHolder.OnPictureChosenListener
import kotlinx.android.synthetic.main.picture_item.view.*
import java.util.*

class PictureRecyclerAdapter(
    private val listener: OnPictureChosenListener
) : RecyclerView.Adapter<PictureViewHolder>() {

    private val items = ArrayList<Item>()

    fun setItems(items: List<Item>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PictureViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.picture_item, parent, false)
        return PictureViewHolder(view, listener)
    }

    override fun onBindViewHolder(holder: PictureViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }
}

class PictureViewHolder(
    itemView: View,
    listener: OnPictureChosenListener
) : RecyclerView.ViewHolder(itemView) {

    init {
        itemView.setOnClickListener {
            listener.onPictureChosen(item)
        }
    }

    lateinit var item: Item

    fun bind(item: Item) {
        this.item = item
        Glide.with(itemView)
            .load(item.link)
            .transform(CenterCrop(), RoundedCorners(15))
            .into(itemView.ivPicture)
    }

    interface OnPictureChosenListener {
        fun onPictureChosen(item: Item)
    }
}