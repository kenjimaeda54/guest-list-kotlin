package com.example.guestlist.service.repository

import androidx.room.*
import androidx.room.Dao
import com.example.guestlist.service.model.GuestModel


@Dao
interface Dao {

    @Insert()
    fun salve(guest: GuestModel): Long

    @Update()
    fun update(guest: GuestModel): Int

    @Delete
    fun delete(guest: GuestModel)

    //esse :id corresponde ao valor que eta no fun geUser(id: Int) se fosse batata seria batata aqui
    @Query("Select * From Guest Where  id = :id")
    fun getUser(id: Int): GuestModel

    @Query("Select * From Guest")
    fun getAll(): List<GuestModel>

    @Query("Select * From Guest Where presence = 1")
    fun getPresents(): List<GuestModel>

    @Query("Select * From Guest Where presence = 0")
    fun getAbsents(): List<GuestModel>
}