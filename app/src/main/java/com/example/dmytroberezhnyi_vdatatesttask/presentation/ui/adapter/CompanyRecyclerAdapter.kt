package com.example.dmytroberezhnyi_vdatatesttask.presentation.ui.adapter

import android.util.TypedValue
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dmytroberezhnyi_vdatatesttask.data.entity.Company
import com.example.dmytroberezhnyi_vdatatesttask.databinding.CompanyItemBinding
import java.util.*

class CompanyRecyclerAdapter(
    private val companySize: CompanySize,
    private val listener: OnCompanyItemPressedListener? = null
) : RecyclerView.Adapter<CompanyRecyclerAdapter.CompanyViewHolder>() {

    private val items = ArrayList<Company>()

    fun setItems(items: List<Company>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CompanyViewHolder {
        val binding = CompanyItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CompanyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CompanyViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    interface OnCompanyItemPressedListener {
        fun onCompanyItemLongPressed(company: Company)

        fun onCompanyItemClicked(company: Company)
    }

    enum class CompanySize {
        SMALL,
        NORMAL
    }

    inner class CompanyViewHolder(val binding: CompanyItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(company: Company) {
            binding.root.setOnLongClickListener {
                listener?.onCompanyItemLongPressed(company)
                true
            }

            binding.root.setOnClickListener {
                listener?.onCompanyItemClicked(company)
            }

            binding.tvCompanyName.text = company.companyName

            var cardViewHeight = 0
            if (companySize == CompanySize.SMALL) {
                cardViewHeight = 25
            } else if (companySize == CompanySize.NORMAL) {
                cardViewHeight = 50
            }

            binding.cvCompany.layoutParams.height =
                TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP,
                    cardViewHeight.toFloat(),
                    itemView.context.resources.displayMetrics
                ).toInt()
        }
    }
}