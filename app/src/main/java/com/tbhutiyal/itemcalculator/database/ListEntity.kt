package com.tbhutiyal.itemcalculator.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Item")
data class ListEntity(
    @PrimaryKey(autoGenerate= true) val item_id: Int,
    @ColumnInfo(name = "item_name") val item_name:String,
    @ColumnInfo(name = "item_price") val price:String,
    @ColumnInfo(name= "units") val units: String
)