package ru.abenda.marsexplorer.data.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import ru.abenda.marsexplorer.data.enums.CameraType
import ru.abenda.marsexplorer.data.enums.RoverType

@Entity(
    tableName = "photos_stats_by_sol",
    primaryKeys = ["rover_type", "sol"]
)
data class PhotosStatsBySol(
    @ColumnInfo(name = "rover_type") val roverType: RoverType,
    val sol: Int,
    @ColumnInfo(name = "total_photos") val totalPhotos: Int,
    val cameras: List<CameraType>
)
