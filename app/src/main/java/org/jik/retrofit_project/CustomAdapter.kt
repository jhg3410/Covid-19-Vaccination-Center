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

class CustomAdapter(private var dataList : List<DataX>, private val context:Context) : RecyclerView.Adapter<CustomAdapter.Holder>(),Filterable{

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_recycler,parent,false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val data = dataList[position]
        holder.setData(data,context)
    }

    override fun getItemCount(): Int {

        return dataList.size
    }

    class Holder(itemView: View?) : RecyclerView.ViewHolder(itemView!!){
        fun setData(data: DataX?, context: Context) {
            itemView.findViewById<TextView>(R.id.address).text = data?.address
            itemView.findViewById<TextView>(R.id.centerName).text = data?.centerName
            itemView.findViewById<TextView>(R.id.phoneNumber).text = data?.phoneNumber
        }
    }

    override fun getFilter(): Filter {
        TODO("Not yet implemented")
    }


}