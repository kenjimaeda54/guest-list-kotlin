package com.example.guestlist.service.repository

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

abstract  class GetDataBase: RoomDatabase(){


    companion object{
        private  lateinit var  INSTANCE: GetDataBase
        fun getDatabase(context: Context): GetDataBase{
            if(!::INSTANCE.isInitialized){
                INSTANCE= Room.databaseBuilder(context,GetDataBase::class.java,"guest")
                    .allowMainThreadQueries() //room entende que banco de dados e pesado então roda em thread separada
                    .build()
            }
            return INSTANCE
        }

    }


}