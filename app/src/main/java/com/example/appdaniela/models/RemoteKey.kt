package com.example.appdaniela.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "remote_keys")
data class RemoteKey(
    @PrimaryKey val roomModelId: String,
    val prevKey: Int?,
    val nextKey: Int?
)
