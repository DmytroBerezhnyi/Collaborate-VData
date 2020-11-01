package com.example.dmytroberezhnyi_vdatatesttask.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dmytroberezhnyi_vdatatesttask.R
import com.example.dmytroberezhnyi_vdatatesttask.data.entity.Company
import kotlinx.android.synthetic.main.company_item.view.*
import java.util.*

class CompanyRecyclerAdapter : RecyclerView.Adapter<CompanyViewHolder>() {

    private val items = ArrayList<Company>()

    fun setItems(items: List<Company>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CompanyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.company_item, parent, false)
        return CompanyViewHolder(view)
    }

    override fun onBindViewHolder(holder: CompanyViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }
}

class CompanyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    lateinit var company: Company

    fun bind(company: Company) {
        this.company = company
        itemView.tvCompanyName.text = company.companyName
    }
}