package com.example.appdaniela.utils.roomDb.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.appdaniela.models.UserDto

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(users: List<UserDto>)

    @Query("SELECT * FROM users WHERE id = :userId")
    suspend fun getByUserId(userId:String):List<UserDto>
}