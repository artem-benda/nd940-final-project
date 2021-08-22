package ru.abenda.marsexplorer.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ru.abenda.marsexplorer.data.db.model.PhotosStatsBySol
import ru.abenda.marsexplorer.data.db.model.RoverManifest
import ru.abenda.marsexplorer.data.enums.RoverType

@Dao
interface RoverManifestDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(manifest: RoverManifest)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStatsBySol(photosStatsBySol: List<PhotosStatsBySol>)

    @Query(
        "SELECT * FROM rovers_manifest WHERE " +
            "rover_type = :roverType "
    )
    fun getById(roverType: RoverType): Flow<RoverManifest>

    @Query(
        "SELECT * FROM photos_stats_by_sol WHERE " +
            "rover_type = :roverType " +
            "ORDER BY sol DESC"
    )
    fun getStatsByRoverType(roverType: RoverType): Flow<List<PhotosStatsBySol>>

    @Query(
        "DELETE FROM rovers_manifest WHERE " +
            "rover_type = :roverType "
    )
    suspend fun deleteById(roverType: RoverType)

    @Query("DELETE FROM rovers_manifest")
    suspend fun clear()

    @Query("DELETE FROM photos_stats_by_sol")
    suspend fun clearStatsBySol()
}
