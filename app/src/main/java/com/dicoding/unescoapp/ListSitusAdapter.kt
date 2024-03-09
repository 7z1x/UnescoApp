package com.dicoding.unescoapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ListSitusAdapter(private val listSitus: ArrayList<Situs>) : RecyclerView.Adapter<ListSitusAdapter.ListViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(callback: OnItemClickCallback) {
        this.onItemClickCallback = callback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_row_situs, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val situs = listSitus[position]

        // Set data ke tampilan ViewHolder sesuai dengan posisi
        holder.bind(situs)

        // Menambahkan event click listener di sini
        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(situs)
        }
    }

    override fun getItemCount(): Int = listSitus.size

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imgPhoto: ImageView = itemView.findViewById(R.id.img_item_photo)
        private val tvName: TextView = itemView.findViewById(R.id.tv_item_name)
        private val tvDescription: TextView = itemView.findViewById(R.id.tv_item_description)

        fun bind(situs: Situs) {
            imgPhoto.setImageResource(situs.photo)
            tvName.text = situs.name
            tvDescription.text = situs.description
        }
    }

    // Di dalam class ListSitusAdapter
    interface OnItemClickCallback {
        fun onItemClicked(data: Situs)
    }
}
