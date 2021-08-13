package ru.abenda.marsexplorer.data.db.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.abenda.marsexplorer.data.db.model.RoverPhoto

@Dao
interface RoverPhotosDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(repos: List<RoverPhoto>)

    @Query(
        "SELECT * FROM rover_photos WHERE " +
            "name LIKE :queryString OR description LIKE :queryString " +
            "ORDER BY stars DESC, name ASC"
    )
    fun photosByName(queryString: String): PagingSource<Int, RoverPhoto>

    @Query("DELETE FROM rover_photos")
    suspend fun clear()
}
