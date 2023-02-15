package com.example.appdaniela.viewModels

import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.appdaniela.domain.*
import com.example.appdaniela.models.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged

class IntroViewModel(
    private val setPagingFoodDataAPI:SetPagingFoodDataAPI,
    private val deleteNoneFavouriteFoodsLocal: DeleteNoneFavouriteFoodsLocal,
    private val deleteAllFoodsLocal: DeleteAllFoodsLocal,
    private val deleteAllKeysLocal: DeleteAllKeysLocal,
    ): ViewModel()
{

    var deleteNoneFavouriteFlag = false

    private val _pagingData = MutableLiveData<PagingData<FoodModDto>>()
    val pagingData: LiveData<PagingData<FoodModDto>>
        get() = _pagingData

    fun setPagingPostData(){
        viewModelScope.launch(Dispatchers.IO) {
           setPagingFoodDataAPI.execute({deleteNoneFavouriteFlag},{deleteNoneFavouriteFlag = it})
               .cachedIn(this)
               .distinctUntilChanged()
               .collectLatest { validatePagingPostsResult(it) }
        }
    }

    private fun validatePagingPostsResult(result:PagingData<FoodModDto>){
        viewModelScope.launch(Dispatchers.Main) {
            _pagingData.value = result
        }
    }

    fun deleteNoneFavouritesPostsOfDataBase(){
        deleteNoneFavouriteFlag = true
        viewModelScope.launch(Dispatchers.IO) {
            deleteNoneFavouriteFoodsLocal.execute()
        }
    }

    fun deleteAllPostsOfDataBase(){
        viewModelScope.launch(Dispatchers.IO) {
            deleteAllFoodsLocal.execute()
        }
    }

    fun deleteAllKeysOfDatabase(){
        viewModelScope.launch(Dispatchers.IO) {
            deleteAllKeysLocal.execute()
        }
    }


}