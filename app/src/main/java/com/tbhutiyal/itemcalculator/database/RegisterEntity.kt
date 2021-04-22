package com.tbhutiyal.itemcalculator.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Register")
data class RegisterEntity(
    @PrimaryKey(autoGenerate = true) var user_id:Int =0,
    @ColumnInfo(name = "mobile") var mobile:String,
    @ColumnInfo(name = "name") var name:String,
    @ColumnInfo(name = "password") var password:String
)