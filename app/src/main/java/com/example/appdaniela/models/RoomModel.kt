package com.example.appdaniela.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "roomModel")
data class RoomModel(
    @PrimaryKey(autoGenerate = false) var id: String = "",
    var title: String = "",
    var imageUrl:String = "",
    var description:String = "",
    var prices:String = ""
):Serializable




fun Comic.gitRepListInfo2RoomModel():RoomModel{
    return RoomModel(
        id = id,
        title = title?:"" ,
        imageUrl = if(images!!.isNotEmpty())"${images[0].path}.${images[0].extension}" else "https://imagenesparaperfildewasap.com/wp-content/uploads/no-hay-foto-3.png",
        prices = if(prices!!.isNotEmpty())"${prices[0].price} ${prices[0].type}" else "No hay datos del precio",
        description = if(description.isNullOrBlank())"No hay descripción" else description
    )
}
