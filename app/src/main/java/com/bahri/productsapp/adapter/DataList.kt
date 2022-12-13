package com.bahri.productsapp.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bahri.productsapp.R
import com.bahri.productsapp.model.DataProduk
import com.bumptech.glide.Glide

class DataList (private var ListData: ArrayList<DataProduk>):
    RecyclerView.Adapter<DataList.viewHolder>() {

    inner class viewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.iv_image)
        val id: TextView = itemView.findViewById(R.id.tv_id)
        val title: TextView = itemView.findViewById(R.id.tv_title)
        val category: TextView = itemView.findViewById(R.id.tv_category)
        val brand: TextView = itemView.findViewById(R.id.tv_brand)
        val decs: TextView = itemView.findViewById(R.id.tv_desc)
        val disc: TextView = itemView.findViewById(R.id.tv_discount)
        val price: TextView = itemView.findViewById(R.id.tv_price)
        val stock: TextView = itemView.findViewById(R.id.tv_stock)
        val ratting: TextView = itemView.findViewById(R.id.tv_rating)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list,parent,false)
        return viewHolder(view)
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        val data = ListData[position]
        Glide.with(holder.itemView)
            .load(data.thumbnail)
            .into(holder.image)
        holder.id.text = data.id.toString()
        holder.title.text = data.title
        holder.category.text = data.category
        holder.brand.text = data.brand
        holder.decs.text = data.description
        holder.price.text = data.price.toString()
        holder.disc.text = data.discountPercentage.toString()
        holder.stock.text = data.stock.toString()
        holder.ratting.text = data.rating.toString()
    }

    override fun getItemCount() = ListData.size

    @SuppressLint("NotifyDataSetChanged")
    fun setData(data: List<DataProduk>){
        ListData.clear()
        ListData.addAll(data)
        notifyDataSetChanged()
    }
}