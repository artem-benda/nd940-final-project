package ru.abenda.marsexplorer.data.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(tableName = "rover_photos")
data class RoverPhoto(
    @PrimaryKey
    val id: Long,
    val sol: Int,
    val camera: Camera,
    @ColumnInfo(name = "img_src") val imageSrc: String,
    @ColumnInfo(name = "earth_date") val earthDate: LocalDate,
    val rover: Rover
)
