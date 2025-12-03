package com.example.pizzaapp3429

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.nio.ByteOrder

class AdapterTransaction(private val listOrder: List<TransactionModel>): RecyclerView.Adapter<AdapterTransaction.ViewHolderOrder>() {
    class ViewHolderOrder (view: View): RecyclerView.ViewHolder (view){
        val imgFotoMenu : ImageView
        val txtName : TextView
        val txtHarga : TextView
        val txtJml: TextView
        init {
            txtName = view.findViewById(R.id.textViewNamaMenu)
            txtHarga = view.findViewById(R.id.textViewHargaMenu)
            txtJml = view.findViewById(R.id.textQtyMenu)
            imgFotoMenu = view.findViewById(R.id.imageViewMenu)
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AdapterTransaction.ViewHolderOrder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val cellForRow = layoutInflater.inflate(R.layout.card_layout_order,parent,false)

        return ViewHolderOrder(cellForRow)


    }

    override fun onBindViewHolder(
        holder: AdapterTransaction.ViewHolderOrder,
        position: Int
    ) {
        val modelTrx = listOrder[position]
        holder.imgFotoMenu.setImageResource(modelTrx.gambar)
        holder.txtName.text =modelTrx.nama
        holder.txtHarga.text =modelTrx.harga
        holder.txtJml.text =modelTrx.jumlah

    }

    override fun getItemCount(): Int {
       return listOrder.size
    }
}