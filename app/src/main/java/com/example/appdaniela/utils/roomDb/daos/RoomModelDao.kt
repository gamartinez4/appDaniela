package com.example.appdaniela.utils.roomDb.daos

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.appdaniela.models.RoomModel

@Dao
interface RoomModelDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(RoomModels: List<RoomModel>)

    @Query("UPDATE roomModel SET favourite=:favourite WHERE id = :id")
    suspend fun insertElement(favourite:Boolean, id:String)

    @Query("SELECT * FROM roomModel ORDER BY favourite DESC")
    fun getAll(): PagingSource<Int, RoomModel>

    @Query("DELETE FROM roomModel")
    suspend fun deleteAll()

    @Query("DELETE FROM roomModel WHERE favourite = 0")
    suspend fun deleteAllNoneFavourite()
}