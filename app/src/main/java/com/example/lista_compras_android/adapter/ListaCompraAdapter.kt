package com.example.lista_compras_android.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.GONE
import androidx.recyclerview.widget.RecyclerView.VISIBLE
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.lista_compras_android.R
import com.example.lista_compras_android.models.ListaCompra

class ListaCompraAdapter(
    var listasCompra: ArrayList<ListaCompra>,
    val context: Context
): Adapter<ListaCompraAdapter.ListaCompraViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListaCompraViewHolder {
        // inflar o layout do adapter de lista de compra
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_compra, parent, false)

        return ListaCompraViewHolder(view)
    }

    override fun getItemCount(): Int {

        return this.listasCompra.size
    }

    override fun onBindViewHolder(holder: ListaCompraViewHolder, position: Int) {
        val listaCompra: ListaCompra = this.listasCompra.get(position)
        holder.txtNomeListaCompra.text = listaCompra.nome

        if (listaCompra.concluida) {
            holder.iconeListaCompraConcluida.visibility = VISIBLE
        } else {
            holder.iconeListaCompraConcluida.visibility = GONE
        }

    }

    fun atualizarLista(listasCompra: ArrayList<ListaCompra>) {
        this.listasCompra = listasCompra
    }

    class ListaCompraViewHolder(view: View): ViewHolder(view) {

        var txtNomeListaCompra: TextView = view.findViewById(R.id.txt_nome_lista)
        var txtDataCriacao: TextView = view.findViewById(R.id.txt_data_criacao)
        var iconeListaCompraConcluida: ImageView = view.findViewById(R.id.img_lista_compra_concluida)

    }

}