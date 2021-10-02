package ru.abenda.marsexplorer.data.db.converters

import androidx.room.TypeConverter
import ru.abenda.marsexplorer.data.enums.CameraType
import java.lang.IllegalArgumentException

class CameraTypesConverter {
    @TypeConverter
    fun fromCameraTypes(cameraTypes: List<CameraType>): String {
        return cameraTypes.joinToString { it.toString() }
    }

    @TypeConverter
    fun toCameraTypes(cameraTypes: String): List<CameraType> {
        return cameraTypes.split(",")
            .map {
                try {
                    CameraType.valueOf(it.trim())
                } catch (e: IllegalArgumentException) {
                    return@map CameraType.OTHER
                }
            }
            .distinct()
    }
}
