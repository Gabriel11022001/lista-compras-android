package com.example.lista_compras_android

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lista_compras_android.adapter.ListaCompraAdapter
import com.example.lista_compras_android.models.ListaCompra
import com.example.lista_compras_android.repositorio.ListaCompraRepositorio

class MainActivity : AppCompatActivity(), OnClickListener {

    private lateinit var recyclerListaCompras: RecyclerView
    private lateinit var btnAdicionarListaCompra: ImageButton
    private lateinit var btnFecharApp: ImageButton
    private lateinit var btnRetornar: ImageButton
    private lateinit var contexto: Context
    private lateinit var listaCompraRepositorio: ListaCompraRepositorio
    private var listasCompra: ArrayList<ListaCompra> = ArrayList()
    private lateinit var listaCompraAdapter: ListaCompraAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.contexto = applicationContext
        this.mapearElementosInterface()
        this.mapearEventos()
        this.listaCompraRepositorio = ListaCompraRepositorio(contexto)
        // preparar a recyclerview da lista de compras
        this.prepararRecyclerViewListaCompras()
    }

    override fun onStart() {
        super.onStart()
        this.consultarListasCompra()
    }

    private fun prepararRecyclerViewListaCompras() {
        this.listaCompraAdapter = ListaCompraAdapter(this.listasCompra, contexto)
        this.recyclerListaCompras.layoutManager = LinearLayoutManager(contexto)
        this.recyclerListaCompras.adapter = listaCompraAdapter
    }

    private fun mapearElementosInterface() {
        this.btnAdicionarListaCompra = findViewById(R.id.btn_adicionar_menu)
        this.btnRetornar = findViewById(R.id.btn_retornar_menu)
        this.btnFecharApp = findViewById(R.id.btn_fechar_menu)
        this.recyclerListaCompras = findViewById(R.id.recycler_lista_compras)
    }

    private fun mapearEventos() {
        this.btnAdicionarListaCompra.setOnClickListener(this)
        this.btnRetornar.setOnClickListener(this)
        this.btnFecharApp.setOnClickListener(this)
    }

    private fun cadastrarListaCompra() {
        val builderDialogCadastrarListaCompra = AlertDialog.Builder(this)
        val viewAlertDialog = layoutInflater.inflate(R.layout.dialog_cadastrar_lista_compra, null)
        builderDialogCadastrarListaCompra.setView(viewAlertDialog)
        val alertDialogCadastrarListaCompra = builderDialogCadastrarListaCompra.create()
        builderDialogCadastrarListaCompra.setCancelable(false)
        alertDialogCadastrarListaCompra.show()
        val edtNomeListaCompra: EditText = viewAlertDialog.findViewById(R.id.edt_nome_lista_compra)
        val btnCadastrar: Button = viewAlertDialog.findViewById(R.id.btn_cadastrar_lista_compra)
        btnCadastrar.setOnClickListener {
            // mapear o evento de clique no botão de cadastrar lista de compra
            finalizarCadastroListaCompra(edtNomeListaCompra.text.toString().trim())
        }
    }

    private fun finalizarCadastroListaCompra(nomeListaCompra: String) {

        try {

            if (nomeListaCompra.isEmpty()) {

            } else {
                val listaCompra: ListaCompra = ListaCompra()
                listaCompra.nome = nomeListaCompra
                listaCompra.concluida = false
                this.listaCompraRepositorio.cadastrar(listaCompra)
                // redirecionar usuário para a tela para informar os produtos
            }

        } catch (e: Exception) {
            Log.e("erro_cadastrar_lista_compra", e.message.toString())
            // ocorreu um erro ao tentar-se cadastrar a lista de compras
        }

    }

    private fun fecharApp() {

    }

    private fun consultarListasCompra() {

        try {
            this.listasCompra = this.listaCompraRepositorio.listarTodos()

            if (this.listasCompra.size == 0) {
                // não existem listas de compra cadastradas
                Toast.makeText(this.contexto, "Não existem listas de compra cadastradas no banco de dados!", Toast.LENGTH_LONG)
                    .show()
            } else {
                this.listaCompraAdapter.atualizarLista(this.listasCompra)
                this.listaCompraAdapter.notifyDataSetChanged()
            }

        } catch (e: Exception) {
            Log.e("erro_listar_listas_compra", e.message.toString())
            // apresentar alerta de erro
        }

    }

    override fun onClick(p0: View?) {
        val id: Int = p0!!.id

        if (id == R.id.btn_adicionar_menu) {
            this.cadastrarListaCompra()
        } else if (listOf(R.id.btn_fechar_menu, R.id.btn_retornar_menu).contains(id)) {

        }

    }

}