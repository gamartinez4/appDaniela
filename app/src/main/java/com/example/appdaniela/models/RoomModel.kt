package com.example.appdaniela.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Room
import java.io.Serializable

@Entity(tableName = "roomModel")
data class RoomModel (
    @PrimaryKey val id:String,
    val name:String,
    val description:String,
    val private:String,
    val login:String,
    val url:String
    ):Serializable

fun GitRepListInfo.gitRepListInfo2RoomModel():RoomModel{
    return RoomModel(
        id = id,
        name = name,
        description = description,
        private = private,
        login = owner.login,
        url = owner.url
    )
}

fun GitRepModel.gitRepModel2RoomModelList():ArrayList<RoomModel>{
    return ArrayList<RoomModel>().apply {
        this@gitRepModel2RoomModelList.items.forEach{
            this.add(it.gitRepListInfo2RoomModel())
        }
    }
}