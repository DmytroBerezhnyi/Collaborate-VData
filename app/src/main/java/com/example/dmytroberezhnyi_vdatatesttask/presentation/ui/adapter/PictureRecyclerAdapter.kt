package com.example.dmytroberezhnyi_vdatatesttask.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.dmytroberezhnyi_vdatatesttask.data.pojo.Item
import com.example.dmytroberezhnyi_vdatatesttask.databinding.PictureItemBinding
import java.util.*

class PictureRecyclerAdapter(
    private val listener: OnPictureChosenListener
) : RecyclerView.Adapter<PictureRecyclerAdapter.PictureViewHolder>() {

    private val items = ArrayList<Item>()

    fun setItems(items: List<Item>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PictureViewHolder {
        val binding = PictureItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PictureViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PictureViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    interface OnPictureChosenListener {
        fun onPictureChosen(item: Item)
    }

    inner class PictureViewHolder(val binding: PictureItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Item) {

            binding.root.setOnClickListener {
                listener.onPictureChosen(item)
            }

            Glide.with(itemView)
                .load(item.link)
                .transform(CenterCrop(), RoundedCorners(15))
                .into(binding.ivPicture)
        }
    }
}