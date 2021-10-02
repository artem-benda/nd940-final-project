package ru.abenda.marsexplorer.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.abenda.marsexplorer.data.db.model.PhotosStatsBySolThumbnail

@Dao
interface ThumbnailsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(thumbnails: List<PhotosStatsBySolThumbnail>)

    @Query("SELECT * from photos_stats_by_sol_thumbnails where stats_by_sol_id = :statsBySolId")
    suspend fun findByStatsBySolId(statsBySolId: String): List<PhotosStatsBySolThumbnail>
}
