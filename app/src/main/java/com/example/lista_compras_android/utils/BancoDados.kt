package com.example.lista_compras_android.utils

import android.content.Context
import android.database.sqlite.SQLiteDatabase

class BancoDados(val contexto: Context) {

    private val gerenciadorBancoDados: GerenciadorBancoDados = GerenciadorBancoDados(contexto)
    private lateinit var bancoDados: SQLiteDatabase

    init {
        this.conectar()
    }

    fun conectar(): SQLiteDatabase {
        this.bancoDados = this.gerenciadorBancoDados.writableDatabase

        return bancoDados
    }

    fun fecharConexao() {

        this.gerenciadorBancoDados.close()
    }

}