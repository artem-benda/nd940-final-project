package ru.abenda.marsexplorer.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.abenda.marsexplorer.data.db.model.RemoteKey

@Dao
interface RemoteKeysDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(remoteKey: List<RemoteKey>)

    @Query("SELECT * FROM remote_keys WHERE photoId = :photoId")
    suspend fun remoteKeysPhotoId(photoId: Long): RemoteKey?

    @Query("DELETE FROM remote_keys")
    suspend fun clear()
}
