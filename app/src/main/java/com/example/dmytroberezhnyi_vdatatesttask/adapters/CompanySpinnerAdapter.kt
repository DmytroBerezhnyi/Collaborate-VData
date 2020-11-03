package com.example.dmytroberezhnyi_vdatatesttask.adapters

import android.content.Context
import android.widget.ArrayAdapter
import android.widget.Filter
import androidx.annotation.LayoutRes
import com.example.dmytroberezhnyi_vdatatesttask.data.entity.Company

class CompanySpinnerAdapter(
    private val mContext: Context,
    @LayoutRes resource: Int,
    val items: List<Company>
) : ArrayAdapter<Company>(mContext, resource, items) {

    fun setItems(items: List<Company>) {
        clear()
        addAll(items)
    }

    override fun getFilter(): Filter {
        return DisableFilter()
    }

    private inner class DisableFilter : Filter() {

        override fun performFiltering(p0: CharSequence?): FilterResults {
            val result = FilterResults()
            result.values = items
            result.count = items.size
            return result
        }

        override fun publishResults(p0: CharSequence?, p1: FilterResults?) {
            notifyDataSetChanged()
        }
    }
}