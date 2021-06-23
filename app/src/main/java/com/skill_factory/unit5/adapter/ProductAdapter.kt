package com.skill_factory.unit5.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.skill_factory.unit5.R
import com.skill_factory.unit5.model.Add
import com.skill_factory.unit5.model.Item
import com.skill_factory.unit5.model.Product
import java.util.ArrayList

const val ITEM_VIEW_TYPE_PRODUCT = 0
const val ITEM_VEW_TYPE_ADD = 1

 class ProductAdapter(var data: ArrayList<Item>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == ITEM_VIEW_TYPE_PRODUCT)
            return ViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
            )
        else if (viewType == ITEM_VEW_TYPE_ADD) {
            return AddViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_add, parent, false)
            )
        } else {
            throw IllegalArgumentException("Invalid viewType")
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (data[position] is Product) {
            ITEM_VIEW_TYPE_PRODUCT
        } else if (data[position] is Add) {
            ITEM_VEW_TYPE_ADD
        } else {
            throw IllegalArgumentException("Invalid Item View type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == ITEM_VIEW_TYPE_PRODUCT){
            val h = holder as ViewHolder
            val  item = data[position] as Product
            h.icon.setImageResource(item.idIcon)
            h.textName.text = item.name
            h.textDesc.text = item.desc
        }
        else if (getItemViewType(position) == ITEM_VEW_TYPE_ADD){
            val h = holder as AddViewHolder
            val  item = data[position] as Add
            h.textTitle.text = item.title
            h.textContent.text = item.content
        }
    }


    override fun getItemCount(): Int {
        return data.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val icon = itemView.findViewById<ImageView>(R.id.icon)
        val textName = itemView.findViewById<TextView>(R.id.text_name)
        val textDesc = itemView.findViewById<TextView>(R.id.text_desc)
    }

    class AddViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textTitle = itemView.findViewById<TextView>(R.id.title)
        val textContent = itemView.findViewById<TextView>(R.id.content)
    }

}