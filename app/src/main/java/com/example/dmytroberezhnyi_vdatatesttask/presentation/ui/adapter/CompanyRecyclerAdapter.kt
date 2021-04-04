package com.example.dmytroberezhnyi_vdatatesttask.presentation.ui.adapter

import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dmytroberezhnyi_vdatatesttask.R
import com.example.dmytroberezhnyi_vdatatesttask.data.entity.Company
import com.example.dmytroberezhnyi_vdatatesttask.presentation.ui.adapter.CompanyViewHolder.CompanySize
import com.example.dmytroberezhnyi_vdatatesttask.presentation.ui.adapter.CompanyViewHolder.OnCompanyItemPressedListener
import kotlinx.android.synthetic.main.company_item.view.*
import java.util.*

class CompanyRecyclerAdapter(
    private val companySize: CompanySize,
    private val listener: OnCompanyItemPressedListener? = null
) : RecyclerView.Adapter<CompanyViewHolder>() {

    private val items = ArrayList<Company>()

    fun setItems(items: List<Company>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CompanyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.company_item, parent, false)
        return CompanyViewHolder(view, companySize, listener)
    }

    override fun onBindViewHolder(holder: CompanyViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }
}

class CompanyViewHolder(
    itemView: View, private val companySize: CompanySize,
    listener: OnCompanyItemPressedListener?
) : RecyclerView.ViewHolder(itemView) {

    lateinit var company: Company

    init {
        listener?.let {
            itemView.setOnLongClickListener {
                listener.onCompanyItemLongPressed(company)
                true
            }

            itemView.setOnClickListener {
                listener.onCompanyItemClicked(company)
            }
        }
    }

    fun bind(company: Company) {
        this.company = company
        itemView.tvCompanyName.text = company.companyName

        var cardViewHeight = 0
        if (companySize == CompanySize.SMALL) {
            cardViewHeight = 25
        } else if (companySize == CompanySize.NORMAL) {
            cardViewHeight = 50
        }

        itemView.cvCompany.layoutParams.height =
            TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                cardViewHeight.toFloat(),
                itemView.context.resources.displayMetrics
            ).toInt()
    }

    interface OnCompanyItemPressedListener {
        fun onCompanyItemLongPressed(company: Company)

        fun onCompanyItemClicked(company: Company)
    }

    enum class CompanySize {
        SMALL,
        NORMAL
    }
}