package ru.abenda.marsexplorer.data.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.abenda.marsexplorer.data.enums.CameraType

@Entity(tableName = "photos_stats_by_sol")
data class PhotosStatsBySol(
    @PrimaryKey
    val sol: Int,
    @ColumnInfo(name = "total_photos") val totalPhotos: Int,
    val cameras: List<CameraType>
)
