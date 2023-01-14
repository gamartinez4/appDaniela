package com.example.appdaniela.viewModels

import android.util.Log
import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.appdaniela.domain.DeleteAllItemsLocal
import com.example.appdaniela.domain.DeleteAllKeysLocal
import com.example.appdaniela.domain.DeleteNoneFavouriteItemsLocal
import com.example.appdaniela.domain.GetListGitReposAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.example.appdaniela.models.RoomModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged

class IntroViewModel(
    private val getListGitReposAPI:GetListGitReposAPI,
    private val deleteNoneFavouriteItemsLocal: DeleteNoneFavouriteItemsLocal,
    private val deleteAllItemsLocal: DeleteAllItemsLocal,
    private val deleteAllKeysLocal: DeleteAllKeysLocal
    ): ViewModel()
{

    var deleteNoneFavouriteFlag = false

    private val _pagingData = MutableLiveData<PagingData<RoomModel>>()
    val pagingData: LiveData<PagingData<RoomModel>>
        get() = _pagingData

    fun getListRepost(){
        viewModelScope.launch(Dispatchers.IO) {
           getListGitReposAPI.execute({deleteNoneFavouriteFlag},{deleteNoneFavouriteFlag = it})
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

    fun deleteNoneFavouritesItems(){
        deleteNoneFavouriteFlag = true
        viewModelScope.launch(Dispatchers.IO) {
            deleteNoneFavouriteItemsLocal.execute()
        }
    }

    fun deleteAllItems(){
        viewModelScope.launch(Dispatchers.IO) {
            deleteAllItemsLocal.execute()
        }
    }

    fun deleteAllKeys(){
        viewModelScope.launch(Dispatchers.IO) {
            deleteAllKeysLocal.execute()
        }
    }
}