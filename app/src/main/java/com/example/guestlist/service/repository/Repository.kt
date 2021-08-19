package com.example.guestlist.service.repository

import android.content.ContentValues
import android.content.Context
import com.example.convidados.service.constants.DataBaseConstants
import com.example.guestlist.service.model.GuestModel
import java.lang.Exception

class Repository private constructor(context: Context) {

    private var getDatabaseHelper: GetDataBaseHelper = GetDataBaseHelper(context)
    //vamos aplicar o antipattern singleton,quando desejamos concentrar todos nossos logs da aplicao em apenas uma classe
    //criamos esse anti padrão,assim teremos apenas uma instância da classe. E util por exemplo em banco de dados
    //voce fecha seu construtor e cria uma membro estático


    companion object {
        private lateinit var mRepository: Repository
        fun getInstance(context: Context): Repository {
            //por ter declaro minha variável no topo preciso de primeiro criou contexto,entao trato dentro do if
            if (::mRepository.isInitialized) {
                mRepository = Repository(context)
            }
            return mRepository
        }
    }

    fun salve(guest: GuestModel): Boolean {
        return try {
            val db = getDatabaseHelper.writableDatabase
            val constanteValues = ContentValues()
            //id vai ser tratado pelo banco de dados
            constanteValues.put(
                DataBaseConstants.GUEST.COLUMNS.NAME,
                guest.name
            )
            constanteValues.put(
                DataBaseConstants.GUEST.COLUMNS.PRESENCE,
                guest.presence
            )
            db.insert(DataBaseConstants.GUEST.TABLE_NAME, null, constanteValues)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun update(guest: GuestModel): Boolean {
        return try {
            val db = getDatabaseHelper.writableDatabase
            val constanteValues = ContentValues()
            constanteValues.put(
                DataBaseConstants.GUEST.COLUMNS.NAME,
                guest.name
            )
            constanteValues.put(
                DataBaseConstants.GUEST.COLUMNS.PRESENCE,
                guest.presence
            )
            val clause = DataBaseConstants.GUEST.COLUMNS.ID + " = ?"
            val args = arrayOf(guest.id.toString())
            db.update(DataBaseConstants.GUEST.TABLE_NAME, constanteValues, clause, args)
            true
        } catch (e: Exception) {
            false
        }

    }

    fun delete(id: Int): Boolean {
        return try {
            val db = getDatabaseHelper.writableDatabase
            val clause = DataBaseConstants.GUEST.COLUMNS.ID + " = ?"
            val args = arrayOf(id.toString())
            db.delete(DataBaseConstants.GUEST.TABLE_NAME, clause, args)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun getAll(): List<GuestModel> {
        return ArrayList()
    }

    fun getPresents(): List<GuestModel> {
        return ArrayList()
    }

    fun getAbsents(): List<GuestModel> {
        return ArrayList()
    }


}