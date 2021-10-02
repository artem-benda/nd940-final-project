package ru.abenda.marsexplorer.data.db.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import ru.abenda.marsexplorer.data.enums.CameraType
import ru.abenda.marsexplorer.data.enums.RoverType

@Parcelize
@Entity(tableName = "rover_photos")
data class RoverPhoto(
    @PrimaryKey
    val id: Long,
    val sol: Int,
    @ColumnInfo(name = "camera_type") val cameraType: CameraType,
    @ColumnInfo(name = "img_src") val imageSrc: String,
    @ColumnInfo(name = "earth_date") val earthDate: String,
    @ColumnInfo(name = "rover_type") val roverType: RoverType
) : Parcelable
