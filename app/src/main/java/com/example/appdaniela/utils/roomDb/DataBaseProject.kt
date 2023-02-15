package com.example.appdaniela.utils.roomDb

import android.content.Context
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.Room
import com.example.appdaniela.models.*
import com.example.appdaniela.utils.roomDb.daos.RemoteKeyDao
import com.example.appdaniela.utils.roomDb.daos.FoodsDao

@Database(version = 1, entities = [FoodModDto::class, RemoteKey::class])
abstract class DataBaseProject : RoomDatabase() {

    abstract fun getFoodsDao(): FoodsDao
    abstract fun getKeyDao(): RemoteKeyDao

    companion object {
        fun buildDatabase(context: Context):DataBaseProject =
            Room.databaseBuilder(
                context.applicationContext,
                DataBaseProject::class.java,
                "DataBase.db"
            ).build()
    }
}