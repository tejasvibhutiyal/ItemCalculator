package com.tbhutiyal.itemcalculator.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [RegisterEntity::class,ListEntity::class],version = 1,exportSchema = false)

abstract class Database: RoomDatabase(){
    abstract fun dao():Dao

}
