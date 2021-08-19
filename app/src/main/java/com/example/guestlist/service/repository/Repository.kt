package com.example.guestlist.service.repository

import android.content.Context
import com.example.guestlist.service.model.GuestModel

class Repository private constructor(context: Context) {

    private var mDatabase: GetDataBaseHelper = GetDataBaseHelper(context)

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

    fun getAll(): List<GuestModel> {
        return ArrayList()
    }

    fun getPresents(): List<GuestModel> {
        return ArrayList()
    }

    fun getAbsents(): List<GuestModel> {
        return ArrayList()
    }


    fun salve(guest: GuestModel) {

    }

    fun update(guest: GuestModel) {

    }

    fun delete(guest: GuestModel) {

    }

}