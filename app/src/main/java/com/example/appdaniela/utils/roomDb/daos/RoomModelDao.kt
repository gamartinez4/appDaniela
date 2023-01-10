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

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertElement(RoomModel:RoomModel)

    @Query("SELECT * FROM roomModel")
    fun getAll(): PagingSource<Int, RoomModel>

    @Query("DELETE FROM roomModel")
    suspend fun deleteAll()
}