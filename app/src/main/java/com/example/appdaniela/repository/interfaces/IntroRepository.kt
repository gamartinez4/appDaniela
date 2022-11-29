package com.example.appdaniela.repository.interfaces

import androidx.paging.PagingData
import com.example.appdaniela.models.GitRepListInfo
import com.example.appdaniela.models.GitRepModel
import com.example.appdaniela.models.Result
import kotlinx.coroutines.flow.Flow

interface IntroRepository {

    suspend fun getRepos(): Flow<PagingData<GitRepListInfo>>
}