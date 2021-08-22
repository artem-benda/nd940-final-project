package ru.abenda.marsexplorer.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ru.abenda.marsexplorer.data.db.model.RoverManifest
import ru.abenda.marsexplorer.data.enums.RoverType

@Dao
interface RoverManifestDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(manifest: RoverManifest)

    @Query(
        "SELECT * FROM rovers_manifest WHERE " +
            "rover_type = :rover_type "
    )
    fun getById(roverType: RoverType): Flow<RoverManifest>

    @Query(
        "DELETE FROM rovers_manifest WHERE " +
            "rover_type = :rover_type "
    )
    suspend fun deleteById(roverType: RoverType)

    @Query("DELETE FROM rovers_manifest")
    suspend fun clear()
}
