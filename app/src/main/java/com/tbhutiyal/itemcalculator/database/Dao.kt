package com.tbhutiyal.itemcalculator.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
public interface Dao {
    @Insert
    fun insertRegister(vararg registerEntity: RegisterEntity)

    @Delete
    fun deleteRegister(registerEntity: RegisterEntity)

    @Query("SELECT * FROM register")
    fun getallRegister(): List<RegisterEntity>

    @Query("SELECT * FROM register WHERE user_id=:user_id")
    fun getRegisterByIdRegister(user_id:Int): RegisterEntity

    @Insert
    fun insertList(listEntity: ListEntity)

    @Delete
    fun deleteList(listEntity: ListEntity)

    @Query("SELECT * FROM Item")
    fun getAllList():List<ListEntity>
    /* @Query("SELECT EXISTS (SELECT 1 FROM Register WHERE user_id = :id)")
     fun exists(id: Int): Boolean*/

}