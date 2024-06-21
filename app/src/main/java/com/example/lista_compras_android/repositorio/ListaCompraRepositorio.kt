package com.example.lista_compras_android.repositorio

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.example.lista_compras_android.models.ListaCompra
import com.example.lista_compras_android.utils.BancoDados
import java.util.Date

class ListaCompraRepositorio(val contexto: Context): Repositorio() {

    private val bancoDados: BancoDados = BancoDados(contexto)

    fun cadastrar(listaCompra: ListaCompra) {
        val contentValuesListaCompra: ContentValues = ContentValues()
        contentValuesListaCompra.put("nome_lista_compra", listaCompra.nome)
        contentValuesListaCompra.put("concluida", false)
        val banco: SQLiteDatabase = this.bancoDados.conectar()
        banco.insertOrThrow("tb_lista_compra", null, contentValuesListaCompra)
    }

    fun listarTodos(): ArrayList<ListaCompra> {
        val listasCompra: ArrayList<ListaCompra> = ArrayList()
        val banco = this.bancoDados.conectar()
        val query: String = "SELECT * FROM tb_lista_compra ORDER BY id DESC;"
        val cursor: Cursor = banco.rawQuery(query, emptyArray())

        if (cursor != null) {

            while (cursor.moveToNext()) {
                val listaCompra: ListaCompra = ListaCompra()
                listaCompra.id = cursor.getInt(cursor.getColumnIndex("id"))
                listaCompra.nome = cursor.getString(cursor.getColumnIndex("nome_lista"))
                var concluida: Int = cursor.getInt(cursor.getColumnIndex("concluida"))

                if (concluida == 1) {
                    listaCompra.concluida = true
                } else {
                    listaCompra.concluida = false
                }

                listaCompra.dataCriacao = Date()
                listasCompra.add(listaCompra)
            }

            cursor.close()
        }

        this.bancoDados.fecharConexao()

        return listasCompra
    }

    fun buscarPeloId(idLista: Int): ListaCompra {

        return ListaCompra()
    }

    fun alterarNome(idListaCompra: Int, novoNome: String) {

    }

    fun marcarComoConcluida(idListaCompra: Int) {

    }

    fun deletar(idListaCompra: Int) {

    }

}