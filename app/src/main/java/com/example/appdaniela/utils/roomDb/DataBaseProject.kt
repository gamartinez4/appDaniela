package com.example.appdaniela.utils.roomDb

import android.content.Context
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.Room
import com.example.appdaniela.models.CommentDto
import com.example.appdaniela.models.RemoteKey
import com.example.appdaniela.models.PostDto
import com.example.appdaniela.models.UserDto
import com.example.appdaniela.utils.roomDb.daos.CommentDao
import com.example.appdaniela.utils.roomDb.daos.RemoteKeyDao
import com.example.appdaniela.utils.roomDb.daos.PostDao
import com.example.appdaniela.utils.roomDb.daos.UserDao

@Database(version = 1, entities = [PostDto::class, UserDto::class, CommentDto::class,RemoteKey::class])
abstract class DataBaseProject : RoomDatabase() {

    abstract fun getPostDao(): PostDao
    abstract fun getUserDao(): UserDao
    abstract fun getCommentDao(): CommentDao
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