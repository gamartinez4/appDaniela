package com.example.appdaniela.viewModels

import android.util.Log
import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.appdaniela.domain.*
import com.example.appdaniela.models.CommentDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.example.appdaniela.models.PostDto
import com.example.appdaniela.models.Results
import com.example.appdaniela.models.UserDto
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged

class IntroViewModel(
    private val setPagingPostDataAPI:SetPagingPostDataAPI,
    private val deleteNoneFavouriteItemsLocal: DeleteNoneFavouriteItemsLocal,
    private val deleteAllItemsLocal: DeleteAllPostsLocal,
    private val deleteAllKeysLocal: DeleteAllKeysLocal,
    private val getAllCommentsAPI: GetAllCommentsAPI,
    private val setCommentsLocal: SetCommentsLocal,
    private val getAllUsersAPI: GetAllUsersAPI,
    private val setUsersLocal: SetUsersLocal
    ): ViewModel()
{

    var deleteNoneFavouriteFlag = false

    private val _pagingData = MutableLiveData<PagingData<PostDto>>()
    val pagingData: LiveData<PagingData<PostDto>>
        get() = _pagingData

    fun setPagingPostData(){
        viewModelScope.launch(Dispatchers.IO) {
           setPagingPostDataAPI.execute({deleteNoneFavouriteFlag},{deleteNoneFavouriteFlag = it})
               .cachedIn(this)
               .distinctUntilChanged()
               .collectLatest { validatePagingPostsResult(it) }
        }
    }

    private fun validatePagingPostsResult(result:PagingData<PostDto>){
        viewModelScope.launch(Dispatchers.Main) {
            _pagingData.value = result
        }
    }

    fun deleteNoneFavouritesPostsOfDataBase(){
        deleteNoneFavouriteFlag = true
        viewModelScope.launch(Dispatchers.IO) {
            deleteNoneFavouriteItemsLocal.execute()
        }
    }

    fun deleteAllPostsOfDataBase(){
        viewModelScope.launch(Dispatchers.IO) {
            deleteAllItemsLocal.execute()
        }
    }

    fun deleteAllKeysOfDatabase(){
        viewModelScope.launch(Dispatchers.IO) {
            deleteAllKeysLocal.execute()
        }
    }

    fun getCommentsFromApi(){
        viewModelScope.launch(Dispatchers.IO) {
            getAllCommentsAPI.execute().let {
                validateGetCommentsFromApi(it)
            }
        }
    }

    private fun validateGetCommentsFromApi(results: Results<List<CommentDto>>) {
        when (results) {
            is Results.Success -> insertAllCommentsToDataBase(results.data)
            is Results.Failure -> {
                Log.e("error",results.exception.toString())
            }
        }
    }

    private fun insertAllCommentsToDataBase(comments: List<CommentDto>){
        viewModelScope.launch(Dispatchers.IO) {
                setCommentsLocal.execute(comments)
        }
    }

    fun getUsersFromApi(){
        viewModelScope.launch(Dispatchers.IO) {
            getAllUsersAPI.execute().let {
                validateGetUsersFromApi(it)
            }
        }
    }

    private fun validateGetUsersFromApi(results: Results<List<UserDto>>) {
        when (results) {
            is Results.Success -> insertAllUsersToDataBase(results.data)
            is Results.Failure -> {
                Log.e("error",results.exception.toString())
            }
        }
    }

    private fun insertAllUsersToDataBase(comments: List<UserDto>){
        viewModelScope.launch(Dispatchers.IO) {
            setUsersLocal.execute(comments)
        }
    }

}