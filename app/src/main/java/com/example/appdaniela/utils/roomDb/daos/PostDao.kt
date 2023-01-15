package com.example.appdaniela.utils.roomDb.daos

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.appdaniela.models.PostDto

@Dao
interface PostDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(posts: List<PostDto>)

    @Query("UPDATE posts SET favourite=:favourite WHERE id = :id")
    suspend fun insertElement(favourite:Boolean, id:String)

    @Query("SELECT * FROM posts ORDER BY favourite DESC")
    fun getAll(): PagingSource<Int, PostDto>

    @Query("DELETE FROM posts")
    suspend fun deleteAll()

    @Query("DELETE FROM posts WHERE favourite = 0")
    suspend fun deleteAllNoneFavourite()

    @Query("DELETE FROM posts WHERE id = :id")
    suspend fun deletePost(id:String)
}