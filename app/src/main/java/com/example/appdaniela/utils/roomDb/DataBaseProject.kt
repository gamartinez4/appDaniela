package com.example.appdaniela.utils.roomDb

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.appdaniela.models.RemoteKey
import com.example.appdaniela.models.RoomModel
import com.example.appdaniela.utils.roomDb.daos.RemoteKeyDao
import com.example.appdaniela.utils.roomDb.daos.RoomModelDao

@Database(version = 1, entities = [RoomModel::class, RemoteKey::class])
abstract class DataBaseProject : RoomDatabase() {
    abstract fun getRoomModelDao(): RoomModelDao
    abstract fun getKeysDao(): RemoteKeyDao
}