package com.example.lista_compras_android.utils

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class GerenciadorBancoDados(val contexto: Context): SQLiteOpenHelper(
    contexto,
    "db_lista_compras",
    null,
    1
) {

    override fun onCreate(bancoDados: SQLiteDatabase?) {
        // criar as tabelas
        this.criarTabelaListaCompras(bancoDados!!)
    }

    override fun onUpgrade(bancoDados: SQLiteDatabase?, p1: Int, p2: Int) {
        // atulizar as tabelas
    }

    private fun criarTabelaListaCompras(db: SQLiteDatabase) {
        Log.d("teste", "Iniciando a criação da tabela de lista de compras!")
        val comando: String = "CREATE TABLE IF NOT EXISTS tb_lista_compra(" +
                "id INTEGER PRIMARY KEY," +
                "nome_lista TEXT NOT NULL," +
                "data_criacao_lista DATETIME NOT NULL," +
                "concluida INTEGER NOT NULL DEFAULT 0);"
        db.execSQL(comando)
        Log.d("teste", "Finalizada a criação da tabela de lista de compras!")
    }

}