package com.example.appdaniela.repository.interfaces

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.appdaniela.models.CommentDto
import com.example.appdaniela.models.Post
import com.example.appdaniela.models.User
import com.example.appdaniela.models.UserDto
import kotlinx.coroutines.flow.Flow

interface DetailsRepository {

    suspend fun setPost(favourite:Boolean, id:String)

    suspend fun setPagingCommentsData(postId:String): Flow<PagingData<CommentDto>>

    suspend fun getUsersFromDataBase(userId:String):List<UserDto>

    suspend fun deletePost(id:String)
}