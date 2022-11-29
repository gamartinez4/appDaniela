package com.example.appdaniela.repository.impl

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.appdaniela.models.GitRepListInfo
import com.example.appdaniela.models.GitRepModel
import com.example.appdaniela.models.Result
import com.example.appdaniela.pagerSources.GitRepoPagerSource
import com.example.appdaniela.proxy.ApiServices
import com.example.appdaniela.remote.IntroRepoDataSource
import com.example.appdaniela.repository.interfaces.IntroRepository
import kotlinx.coroutines.flow.Flow

class IntroRepositoryImpl(
    private val apiServices: ApiServices
) : IntroRepository{
    override suspend fun getRepos(): Flow<PagingData<GitRepListInfo>> {
        return Pager(config = PagingConfig(pageSize = 20, prefetchDistance = 2),
            pagingSourceFactory = { GitRepoPagerSource(apiServices) }
        ).flow
    }
}