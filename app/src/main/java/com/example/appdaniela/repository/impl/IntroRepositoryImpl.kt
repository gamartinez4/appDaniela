package com.example.appdaniela.repository.impl

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.appdaniela.models.CommentDto
import com.example.appdaniela.models.PostDto
import com.example.appdaniela.models.Results
import com.example.appdaniela.models.UserDto
import com.example.appdaniela.remote.IntroDataSource
import com.example.appdaniela.repository.interfaces.IntroRepository
import com.example.appdaniela.utils.remoteMediators.PostRemoteMediator
import com.example.appdaniela.utils.roomDb.daos.CommentDao
import com.example.appdaniela.utils.roomDb.daos.RemoteKeyDao
import com.example.appdaniela.utils.roomDb.daos.PostDao
import com.example.appdaniela.utils.roomDb.daos.UserDao
import kotlinx.coroutines.flow.Flow

class IntroRepositoryImpl(
    private val postDao: PostDao,
    private val userDao:UserDao,
    private val commentDao: CommentDao,
    private val remoteKeyDao: RemoteKeyDao,
    private val introRepoDataSource: IntroDataSource
) : IntroRepository{

    override suspend fun deleteAllPosts() {
        postDao.deleteAll()
    }

    override suspend fun deleteAllKeys() {
        remoteKeyDao.deleteAll()
    }

    override suspend fun deleteAllNoneFavourite() {
        postDao.deleteAllNoneFavourite()
    }


    @ExperimentalPagingApi
    override suspend fun setPagingPostData(
            deleteNoneFavouriteItemsFun:()->Boolean,
            setDeleteNoneFavouriteItemsFlag:(value:Boolean)->Unit)
    : Flow<PagingData<PostDto>> {
        return Pager(
            config = PagingConfig(pageSize = 10, prefetchDistance = 30),
            remoteMediator =
                PostRemoteMediator(
                    postDao,
                    remoteKeyDao,
                    introRepoDataSource,
                    deleteNoneFavouriteItemsFun,
                    setDeleteNoneFavouriteItemsFlag
                ),
            pagingSourceFactory = { postDao.getAll() }
        ).flow
    }

    override suspend fun getUsers(): Results<List<UserDto>> {
        return introRepoDataSource.getUsers()
    }

    override suspend fun setUsers(users:List<UserDto>){
        userDao.insertAll(users)
    }

    override suspend fun getComments(): Results<List<CommentDto>>{
        return introRepoDataSource.getComments()
    }

    override suspend fun setComments(comments:List<CommentDto>) {
        commentDao.insertAll(comments)
    }



}