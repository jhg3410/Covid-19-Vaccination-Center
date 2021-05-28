package org.jik.retrofit_project

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CustomAdapter : RecyclerView.Adapter<CustomAdapter.Holder>(),Filterable{
    var filteredList = dum.dum
    var unFilteredList = dum.dum

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_recycler, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val data = filteredList[position]
        holder.setData(data)
    }


    override fun getItemCount(): Int = filteredList.size

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charString = constraint.toString()
                if (charString.isEmpty()) {
                    filteredList = unFilteredList
                } else {
                    val filteringList = mutableListOf<DataX>()
                    for (item in unFilteredList) {
                        if (item.address.contains(charString)) filteringList.add(item)
                    }
                    filteredList = filteringList
                }
                val filterResults = FilterResults()
                filterResults.values = filteredList
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                filteredList = results?.values as MutableList<DataX>
                notifyDataSetChanged()
            }
        }
    }

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val phone = itemView.findViewById<TextView>(R.id.phoneNumber)
        val address = itemView.findViewById<TextView>(R.id.address)
        val center = itemView.findViewById<TextView>(R.id.centerName)
        fun setData(data: DataX?) {
            address.text = data?.address
            center.text = data?.centerName
            phone.text = data?.phoneNumber
        }
    }
}