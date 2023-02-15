package com.example.appdaniela.utils.roomDb.daos

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.appdaniela.models.FoodModDto

@Dao
interface FoodsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(posts: List<FoodModDto>)

    @Query("UPDATE foods SET favourite=:favourite WHERE id = :id")
    suspend fun insertElement(favourite:Boolean, id:String)

    @Query("SELECT * FROM foods ORDER BY favourite DESC")
    fun getAll(): PagingSource<Int, FoodModDto>

    @Query("DELETE FROM foods")
    suspend fun deleteAll()

    @Query("DELETE FROM foods WHERE favourite = 0")
    suspend fun deleteAllNoneFavourite()

    @Query("DELETE FROM foods WHERE id = :id")
    suspend fun deletePost(id:String)
}