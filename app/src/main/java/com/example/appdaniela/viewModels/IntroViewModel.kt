package com.example.appdaniela.viewModels

import android.util.Log
import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.appdaniela.domain.GetListGitReposAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.example.appdaniela.models.Result
import com.example.appdaniela.models.RoomModel
import com.example.appdaniela.utils.roomDb.daos.RoomModelDao
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged

class IntroViewModel(
    private val getListGitReposAPI:GetListGitReposAPI
    ): ViewModel()
{
    private val _pagingData = MutableLiveData<PagingData<RoomModel>>()
    val pagingData: LiveData<PagingData<RoomModel>>
        get() = _pagingData

    fun getListRepost(){
        viewModelScope.launch(Dispatchers.IO) {
           getListGitReposAPI.execute()
               .cachedIn(this)
               .distinctUntilChanged()
               .collectLatest { validateResult(it) }
        }
    }

    fun validateResult(result:PagingData<RoomModel>){
        viewModelScope.launch(Dispatchers.Main) {
            _pagingData.value = result
        }
    }
}