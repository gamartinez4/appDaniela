package com.example.appdaniela.repository.impl

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.appdaniela.models.CommentDto
import com.example.appdaniela.models.UserDto
import com.example.appdaniela.repository.interfaces.DetailsRepository
import com.example.appdaniela.utils.roomDb.daos.CommentDao
import com.example.appdaniela.utils.roomDb.daos.PostDao
import com.example.appdaniela.utils.roomDb.daos.UserDao
import kotlinx.coroutines.flow.Flow

class DetailsRepositoryImpl(
    private val postDtoDao: PostDao,
    private val commentDao: CommentDao,
    private val userDao: UserDao
) : DetailsRepository {

    override suspend fun setPost(favourite:Boolean, id:String) {
        postDtoDao.insertElement(favourite,id)
    }

    override suspend fun setPagingCommentsData(postId:String): Flow<PagingData<CommentDto>> {
        return Pager(
            config = PagingConfig(pageSize = 10, prefetchDistance = 30),
            pagingSourceFactory = { commentDao.getByPostId(postId) }
        ).flow
    }

    override suspend fun getUsersFromDataBase(userId:String):List<UserDto> {
        return userDao.getByUserId(userId)
    }

    override suspend fun deletePost(id:String){
        postDtoDao.deletePost(id)
    }

}