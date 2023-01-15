package com.example.appdaniela.repository.interfaces

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.appdaniela.models.*
import kotlinx.coroutines.flow.Flow

interface IntroRepository {

    suspend fun deleteAllPosts()

    suspend fun deleteAllKeys()

    suspend fun deleteAllNoneFavourite()

    suspend fun setPagingPostData(
        deleteNoneFavouriteItemsFun:()->Boolean,
        setDeleteNoneFavouriteItemsFlag:(value:Boolean)->Unit
    ): Flow<PagingData<PostDto>>

    suspend fun getUsers():Results<List<UserDto>>

    suspend fun setUsers(users:List<UserDto>)

    suspend fun getComments(): Results<List<CommentDto>>

    suspend fun setComments(comments:List<CommentDto>)
}