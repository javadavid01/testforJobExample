package com.example.test.ViewModel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.test.Model.ItemRow
import com.example.test.R

class ItemAdapter : RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

    var items = ArrayList<ItemRow>()

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(row: ItemRow) {
            itemView.findViewById<TextView>(R.id.itemRow).text = row.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_row, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun update(newItems: List<ItemRow>) {
        items.addAll(newItems)
        notifyDataSetChanged()
    }
}