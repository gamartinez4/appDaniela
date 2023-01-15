package com.example.appdaniela.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserDto(
    @PrimaryKey(autoGenerate = false) val id:String = "",
    val name:String = ""
    )

fun User.user2userDto():UserDto{
    return UserDto(
        id = id,
        name = name
    )
}