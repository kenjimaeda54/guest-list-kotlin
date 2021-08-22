package com.example.guestlist.service.repository

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.convidados.service.constants.DataBaseConstants
import com.example.guestlist.service.model.GuestModel

@Database(entities = [GuestModel::class], version = 1)
abstract class GuestDataBase : RoomDatabase() {

    abstract fun guestDao(): Dao

    companion object {
        private lateinit var INSTANCE: GuestDataBase
        fun getDatabase(context: Context): GuestDataBase {
            if (!::INSTANCE.isInitialized) {
                synchronized(GuestDataBase::class) {
                    INSTANCE = Room.databaseBuilder(context, GuestDataBase::class.java, "guest")
                        .allowMainThreadQueries()//permito rodar em thread separada se eu esquecer dessa linha gera erro
                        .build()
                }
            }
            return INSTANCE
        }
    }

}