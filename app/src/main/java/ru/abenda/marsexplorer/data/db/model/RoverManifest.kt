package ru.abenda.marsexplorer.data.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.abenda.marsexplorer.data.enums.RoverType

@Entity(tableName = "rovers_manifest")
data class RoverManifest(
    @PrimaryKey
    @ColumnInfo(name = "rover_type") val roverType: RoverType,
    val name: String,
    @ColumnInfo(name = "landing_date") val landingDate: String,
    @ColumnInfo(name = "launch_date") val launchDate: String,
    val status: String,
    @ColumnInfo(name = "max_sol") val maxSol: Int?,
    @ColumnInfo(name = "max_date") val maxDate: String,
    @ColumnInfo(name = "total_photos") val totalPhotos: Int
)
