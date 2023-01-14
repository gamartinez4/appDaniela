package com.example.appdaniela.utils.roomDb.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.appdaniela.models.RemoteKey

@Dao
interface RemoteKeyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(remoteKey: List<RemoteKey>)

    @Query("SELECT * FROM remote_keys WHERE roomModelId = :id")
    suspend fun remoteKeysId(id: String): RemoteKey?

    @Query("DELETE FROM remote_keys")
    suspend fun deleteAll()

    @Query("SELECT (SELECT COUNT(*) FROM remote_keys) == 0")
    fun isEmpty(): Boolean

}