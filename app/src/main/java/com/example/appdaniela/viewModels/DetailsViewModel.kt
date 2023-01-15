package com.example.appdaniela.viewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.appdaniela.domain.DeleteUserLocal
import com.example.appdaniela.domain.GetUserFromUserIdLocal
import com.example.appdaniela.domain.SetPagingCommentDataAPI
import com.example.appdaniela.domain.SetPostDtoLocal
import com.example.appdaniela.models.CommentDto
import com.example.appdaniela.models.DetailsUI
import com.example.appdaniela.models.PostDto
import com.example.appdaniela.models.UserDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

class DetailsViewModel(
    private val setPostDtoLocal: SetPostDtoLocal,
    private val setPagingCommentDataAPI: SetPagingCommentDataAPI,
    private val getUserFromUserIdLocal: GetUserFromUserIdLocal,
    private val deleteUserLocal: DeleteUserLocal
): ViewModel() {

    val user = MutableLiveData(UserDto())
    val selectedValue = MutableLiveData<PostDto?>()

    private val _uiModel = MutableLiveData<DetailsUI>()
    val uiModel: LiveData<DetailsUI>
        get() = _uiModel

    private val _pagingData = MutableLiveData<PagingData<CommentDto>>()
    val pagingData: LiveData<PagingData<CommentDto>>
        get() = _pagingData

    fun getUserFromUserId(userId:String){
        viewModelScope.launch(Dispatchers.Main) {
            user.value = getUserFromUserIdLocal.execute(userId)[0]
        }
    }

    fun deletePostFromPostList(id:String){
        viewModelScope.launch(Dispatchers.Main) {
            deleteUserLocal.execute(id)
            emitUiState(true)
        }
    }

    private fun updateDtoFavouriteFromPostIdToDataBase(favourite:Boolean, id:String){
        viewModelScope.launch(Dispatchers.IO) {
            setPostDtoLocal.execute(favourite,id)
        }
    }

    fun setPagingCommentData(postDto: PostDto){
        viewModelScope.launch(Dispatchers.IO) {
            setPagingCommentDataAPI.execute(postDto.id)
                .cachedIn(this)
                .distinctUntilChanged()
                .collectLatest { validatePagingPostsResult(it) }
        }
    }

    private fun validatePagingPostsResult(result:PagingData<CommentDto>){
        viewModelScope.launch(Dispatchers.Main) {
            _pagingData.value = result
        }
    }

    fun setFavouriteValueForPost(){
        selectedValue.value = selectedValue.value?.apply {
            this.favourite = !this.favourite
            this
        }
        updateDtoFavouriteFromPostIdToDataBase(
            selectedValue.value!!.favourite,
            selectedValue.value!!.id
        )
    }

    private fun emitUiState(goBack:Boolean = false) {
        viewModelScope.launch(Dispatchers.Main) {
            _uiModel.value = DetailsUI(goBack)
        }
    }

}