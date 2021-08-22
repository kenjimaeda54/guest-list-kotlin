package com.example.guestlist.service.repository


import android.content.Context
import com.example.guestlist.service.model.GuestModel


class Repository private constructor(context: Context) {

    //quem lida com essa entidade e o  guestDa() se possuísse outra entidade,fiaríamos outra classe de repositório
    private var getDatabase = GuestDataBase.getDatabase(context).guestDao()
    //vamos aplicar o antipattern singleton,quando desejamos concentrar todos nossos logs da aplicao em apenas uma classe
    //criamos esse anti padrão,assim teremos apenas uma instância da classe. E util por exemplo em banco de dados
    //voce fecha seu construtor e cria uma membro estático


    companion object {
        private lateinit var mRepository: Repository
        fun getInstance(context: Context): Repository {
            //por ter declaro minha variável no topo preciso de primeiro criou contexto,entao trato dentro do if
            if (!::mRepository.isInitialized) {
                mRepository = Repository(context)
            }
            return mRepository
        }
    }


    fun getUser(id: Int): GuestModel {
        return getDatabase.getUser(id)

    }


    fun salve(guest: GuestModel): Boolean {
        return getDatabase.salve(guest) > 0
    }

    fun update(guest: GuestModel): Boolean {
        return getDatabase.update(guest) > 0

    }

    fun delete(guest: GuestModel) {
        getDatabase.delete(guest)
    }


    fun getAll(): List<GuestModel> {
        return getDatabase.getAll()
    }

    fun getPresents(): List<GuestModel> {
        return getDatabase.getPresents()

    }


    fun getAbsents(): List<GuestModel> {
        return getDatabase.getAbsents()
    }


}