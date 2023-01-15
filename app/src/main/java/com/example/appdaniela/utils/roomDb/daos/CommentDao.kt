package com.example.appdaniela.utils.roomDb.daos

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.appdaniela.models.CommentDto

@Dao
interface CommentDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(RoomModels: List<CommentDto>)

    @Query("SELECT * FROM comments WHERE postId = :postId")
    fun getByPostId(postId:String): PagingSource<Int, CommentDto>
}