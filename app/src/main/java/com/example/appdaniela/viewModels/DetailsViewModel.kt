package com.example.appdaniela.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appdaniela.domain.SetRoomModelLocal
import com.example.appdaniela.models.RoomModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailsViewModel(
    private val setRoomModelLocal: SetRoomModelLocal
): ViewModel() {

    val selectedValue = MutableLiveData<RoomModel>()

    fun setRoomModel(roomModel: RoomModel){
        viewModelScope.launch(Dispatchers.IO) {
            setRoomModelLocal.execute(roomModel)
        }
    }

}