package org.jik.retrofit_project

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CustomAdapter : RecyclerView.Adapter<CustomAdapter.Holder>(),Filterable{

    var exam = dum.dum
    var filteredList = exam
    val unFilteredList = exam
   

    init {
        Log.d("설마", filteredList.toString())
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_recycler, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val data = filteredList[position]

        holder.setData(data)
    }

    override fun getItemCount(): Int {
        return filteredList.size
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charString = constraint.toString()
                if (charString.isEmpty()) { //⑶
                    filteredList = unFilteredList
                } else {
                    var filteringList = mutableListOf<DataX>()
                    for (item in unFilteredList) {
                        if (item.address.contains(charString)){
                            filteringList.add(item)
                        }
                    }
                    filteredList = filteringList
                }
                val filterResults = FilterResults()
                filterResults.values = filteredList
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                filteredList = results?.values as MutableList<DataX>
                CustomAdapter().notifyDataSetChanged()
                Log.d("아이템", filteredList.toString())
            }
        }
    }

    class Holder(itemView: View?) : RecyclerView.ViewHolder(itemView!!){
        fun setData(data: DataX?) {
            itemView.findViewById<TextView>(R.id.address).text = data?.address
            itemView.findViewById<TextView>(R.id.centerName).text = data?.centerName
            itemView.findViewById<TextView>(R.id.phoneNumber).text = data?.phoneNumber
        }
    }

}