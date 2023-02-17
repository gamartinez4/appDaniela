package com.example.appdaniela.viewModels

import android.util.Log
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
    private val changeNotifyFilterLocal: ChangeNotifyFilterLocal
    ): ViewModel()
{

    var deleteNoneFavouriteFlag = false

    private val _pagingData = MutableLiveData<PagingData<FoodModDto>>()
    val pagingData: LiveData<PagingData<FoodModDto>>
        get() = _pagingData

    val filterValue = MutableLiveData ("")

    fun setPagingPostData(){
        viewModelScope.launch(Dispatchers.IO) {
           setPagingFoodDataAPI.execute(
               {deleteNoneFavouriteFlag},
               {deleteNoneFavouriteFlag = it},
               {filterValue.value!!}
           )
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

    private fun changeNotifyFilter(value:String){
        viewModelScope.launch(Dispatchers.IO) {
            changeNotifyFilterLocal.execute(value)
        }
    }

    fun fieldTextChanged(charSequence: CharSequence, idField: Int) {
        filterValue.value = charSequence.toString()
        changeNotifyFilter(filterValue.value.toString())
    }


}