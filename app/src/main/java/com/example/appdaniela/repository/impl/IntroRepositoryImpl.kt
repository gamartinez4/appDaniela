package com.example.appdaniela.repository.impl

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.appdaniela.models.RoomModel
import com.example.appdaniela.remote.IntroRepoDataSource
import com.example.appdaniela.repository.interfaces.IntroRepository
import com.example.appdaniela.utils.pagerSources.PagerMediator
import com.example.appdaniela.utils.roomDb.daos.RemoteKeyDao
import com.example.appdaniela.utils.roomDb.daos.RoomModelDao
import com.example.appdaniela.viewModels.IntroViewModel
import kotlinx.coroutines.flow.Flow

class IntroRepositoryImpl(
    private val roomModelDao: RoomModelDao,
    private val remoteKeyDao: RemoteKeyDao,
    private val introRepoDataSource: IntroRepoDataSource
) : IntroRepository{
    override suspend fun deleteAllItems() {
        roomModelDao.deleteAll()
    }

    override suspend fun deleteAllKeys() {
        remoteKeyDao.deleteAll()
    }

    override suspend fun deleteAllNoneFavourite() {
        roomModelDao.deleteAllNoneFavourite()
    }


    @ExperimentalPagingApi
    override suspend fun getRepos(viewModel: IntroViewModel): Flow<PagingData<RoomModel>> {
        return Pager(
            config = PagingConfig(pageSize = 10, prefetchDistance = 30),
            remoteMediator = PagerMediator(roomModelDao,remoteKeyDao,introRepoDataSource,viewModel),
            pagingSourceFactory = {roomModelDao.getAll()}
        ).flow
    }
}