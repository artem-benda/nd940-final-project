package ru.abenda.marsexplorer.data.db.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.abenda.marsexplorer.data.db.model.RoverPhoto
import ru.abenda.marsexplorer.data.enums.CameraType
import ru.abenda.marsexplorer.data.enums.RoverType

@Dao
interface RoverPhotosDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(photos: List<RoverPhoto>)

    @Query(
        "SELECT * FROM rover_photos WHERE " +
            "rover_type = :rover_type AND " +
            "camera_type = :cameraType AND " +
            "sol = :sol " +
            "ORDER BY id DESC, name ASC"
    )
    fun findPhotos(roverType: RoverType, cameraType: CameraType, sol: Int): PagingSource<Int, RoverPhoto>

    @Query("DELETE FROM rover_photos")
    suspend fun clear()
}
