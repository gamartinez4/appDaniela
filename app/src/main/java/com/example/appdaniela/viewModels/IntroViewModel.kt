package com.example.appdaniela.viewModels

import android.util.Log
import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.appdaniela.domain.GetListGitReposAPI
import com.example.appdaniela.models.GitRepListInfo
import com.example.appdaniela.models.GitRepModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.example.appdaniela.models.Result
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged

class IntroViewModel(
    private val getListGitReposAPI:GetListGitReposAPI
    ): ViewModel()
{
    private val _pagingData = MutableLiveData<PagingData<GitRepListInfo>>()
    val pagingData: LiveData<PagingData<GitRepListInfo>>
        get() = _pagingData

    fun getListRepost(){
        viewModelScope.launch(Dispatchers.IO) {
           getListGitReposAPI.execute()
               .cachedIn(this)
               .distinctUntilChanged()
               .collectLatest { validateResult(it) }

        }
    }

    fun validateResult(result:PagingData<GitRepListInfo>){
        viewModelScope.launch(Dispatchers.Main) {
            _pagingData.value = result
        }
    }
}