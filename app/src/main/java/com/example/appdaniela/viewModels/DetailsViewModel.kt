package com.example.appdaniela.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appdaniela.domain.DeleteFoodLocal
import com.example.appdaniela.domain.SetFoodDtoLocal
import com.example.appdaniela.models.DetailsUI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailsViewModel(
    private val setFoodDtoLocal: SetFoodDtoLocal,
    private val deleteUserLocal: DeleteFoodLocal
): ViewModel() {

    private val _uiModel = MutableLiveData<DetailsUI>()
    val uiModel: LiveData<DetailsUI>
        get() = _uiModel



    fun deleteFoodFromFoodList(id:String){
        viewModelScope.launch(Dispatchers.Main) {
            deleteUserLocal.execute(id)
            emitUiState(true)
        }
    }

    private fun updateDtoFavouriteFromPostIdToDataBase(favourite:Boolean, id:String){
        viewModelScope.launch(Dispatchers.IO) {
            setFoodDtoLocal.execute(favourite,id)
        }
    }


    private fun emitUiState(goBack:Boolean = false) {
        viewModelScope.launch(Dispatchers.Main) {
            _uiModel.value = DetailsUI(goBack)
        }
    }

}