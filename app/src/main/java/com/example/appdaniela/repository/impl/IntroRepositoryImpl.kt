package com.example.appdaniela.repository.impl

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.appdaniela.models.FoodModDto
import com.example.appdaniela.remote.IntroDataSource
import com.example.appdaniela.repository.interfaces.IntroRepository
import com.example.appdaniela.utils.remoteMediators.PostRemoteMediator
import com.example.appdaniela.utils.roomDb.daos.RemoteKeyDao
import com.example.appdaniela.utils.roomDb.daos.FoodsDao
import kotlinx.coroutines.flow.Flow

class IntroRepositoryImpl(
    private val foodsDao: FoodsDao,
    private val remoteKeyDao: RemoteKeyDao,
    private val introDataSource: IntroDataSource
) : IntroRepository{

    override suspend fun deleteAllPosts() {
        foodsDao.deleteAll()
    }

    override suspend fun deleteAllKeys() {
        remoteKeyDao.deleteAll()
    }

    override suspend fun deleteAllNoneFavourite() {
        foodsDao.deleteAllNoneFavourite()
    }


    @ExperimentalPagingApi
    override suspend fun setPagingPostData(
            deleteNoneFavouriteItemsFun:()->Boolean,
            setDeleteNoneFavouriteItemsFlag:(value:Boolean)->Unit)
    : Flow<PagingData<FoodModDto>> {
        return Pager(
            config = PagingConfig(pageSize = 10, prefetchDistance = 30),
            remoteMediator =
                PostRemoteMediator(
                    foodsDao,
                    remoteKeyDao,
                    introDataSource,
                    deleteNoneFavouriteItemsFun,
                    setDeleteNoneFavouriteItemsFlag
                ),
            pagingSourceFactory = { foodsDao.getAll() }
        ).flow
    }






}