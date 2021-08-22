package ru.abenda.marsexplorer.data.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.abenda.marsexplorer.data.enums.RoverType
import java.time.LocalDate

@Entity(tableName = "rovers_manifest")
data class RoverManifest(
    @PrimaryKey
    val roverType: RoverType,
    val name: String,
    @ColumnInfo(name = "landing_date") val landingDate: LocalDate,
    @ColumnInfo(name = "launch_date") val launchDate: LocalDate,
    val status: String,
    @ColumnInfo(name = "max_sol") val maxSol: Int?,
    @ColumnInfo(name = "max_date") val maxDate: LocalDate,
    @ColumnInfo(name = "total_photos") val totalPhotos: Int,
    val photos: List<PhotosStatsBySol>
)
