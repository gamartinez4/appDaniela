package com.example.appdaniela.repository.impl

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.appdaniela.models.GitRepListInfo
import com.example.appdaniela.models.GitRepModel
import com.example.appdaniela.models.Result
import com.example.appdaniela.models.RoomModel
import com.example.appdaniela.proxy.ApiServices
import com.example.appdaniela.remote.IntroRepoDataSource
import com.example.appdaniela.repository.interfaces.IntroRepository
import com.example.appdaniela.utils.pagerSources.PagerMediator
import com.example.appdaniela.utils.roomDb.DataBaseProject
import kotlinx.coroutines.flow.Flow

class IntroRepositoryImpl(
    private val apiServices: ApiServices,
    private val dataBase: DataBaseProject
) : IntroRepository{

    @ExperimentalPagingApi
    override suspend fun getRepos(): Flow<PagingData<RoomModel>> {
        return Pager(
            config = PagingConfig(pageSize = 10, prefetchDistance = 2),
            remoteMediator = PagerMediator(apiServices,dataBase) ,
            pagingSourceFactory = {dataBase.getRoomModelDao().getAll()}
        ).flow
    }
}