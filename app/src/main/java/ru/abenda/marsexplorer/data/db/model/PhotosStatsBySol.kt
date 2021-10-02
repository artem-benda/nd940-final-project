package ru.abenda.marsexplorer.data.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import ru.abenda.marsexplorer.data.enums.CameraType
import ru.abenda.marsexplorer.data.enums.RoverType

@Entity(
    tableName = "photos_stats_by_sol",
    indices = [
        Index("rover_type", "sol", name = "idx_unq_rover_type_sol", unique = true)
    ]
)
data class PhotosStatsBySol(
    @PrimaryKey val id: String,
    @ColumnInfo(name = "rover_type") val roverType: RoverType,
    val sol: Int,
    @ColumnInfo(name = "total_photos") val totalPhotos: Int,
    val cameras: List<CameraType>
)
