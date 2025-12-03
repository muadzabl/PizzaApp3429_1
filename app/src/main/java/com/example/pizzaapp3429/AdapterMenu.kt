package com.example.pizzaapp3429

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class AdapterMenu(private val listMenu: List<MenuModel>): RecyclerView.Adapter<AdapterMenu.ViewHolder>() {
    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val imgGambar: ImageView
        val txtName: TextView
        val txtHarga: TextView
        val context = view.context
        init {
            imgGambar = view.findViewById(R.id.imageViewMenu)
            txtName = view.findViewById(R.id.textViewNamaMenu)
            txtHarga = view.findViewById(R.id.textViewHargaMenu)
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AdapterMenu.ViewHolder {
        val layInflite = LayoutInflater.from(parent.context)
        val cellForRow = layInflite.inflate(R.layout.card_layout_menu,
            parent,false)
        return ViewHolder(cellForRow)
    }

    override fun onBindViewHolder(holder: AdapterMenu.ViewHolder, position: Int) {
       val model = listMenu[position]
        holder.imgGambar.setImageResource(model.gambar)
        holder.txtName.text = model.nama
        holder.txtHarga.text = model.harga
    }

    override fun getItemCount(): Int {
        return  listMenu.size
    }
}