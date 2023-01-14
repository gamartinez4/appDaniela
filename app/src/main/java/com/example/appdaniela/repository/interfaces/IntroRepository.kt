package com.example.appdaniela.repository.interfaces

import androidx.paging.PagingData
import com.example.appdaniela.models.Result
import com.example.appdaniela.models.RoomModel
import com.example.appdaniela.viewModels.IntroViewModel
import kotlinx.coroutines.flow.Flow

interface IntroRepository {

    suspend fun deleteAllItems()

    suspend fun deleteAllKeys()

    suspend fun deleteAllNoneFavourite()

    suspend fun getRepos(viewModel: IntroViewModel): Flow<PagingData<RoomModel>>
}