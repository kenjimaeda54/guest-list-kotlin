package com.example.guestlist.service.repository

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.convidados.service.constants.DataBaseConstants

class GetDataBaseHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NOME, null, DATABASE_VERSION) {

    //se nos meus testes precisar  faturar preciso desistalar aplicacao,porque o método onCreate e so chamado uma vez.
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(CREATE_TABLE_GUEST)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {

    }

    companion object {
        //versao do database e a versao do nosso banco de dados,por exemplo
        //caso desejo atualizar para acrescentar novas fetatures no banco,para nao perder
        //os novos usuários mudo a versao,assim sera o método onUpgrade
        //que sera chamado. Nao correndo risco de perder nenhum dados
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NOME = "Convidados.db"

        private const val CREATE_TABLE_GUEST =
            ("create table " + DataBaseConstants.GUEST.TABLE_NAME + " ("
                    + DataBaseConstants.GUEST.COLUMNS.ID + " integer primary key autoincrement, "
                    + DataBaseConstants.GUEST.COLUMNS.NAME + " text, "
                    + DataBaseConstants.GUEST.COLUMNS.PRESENCE + " integer);")

    }


}