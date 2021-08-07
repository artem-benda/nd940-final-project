package ru.abenda.marsexplorer.data.api.dto

import com.squareup.moshi.Json
import ru.abenda.marsexplorer.data.api.enums.CameraType

data class PhotosStatsBySolDto(
    val sol: Int,
    @Json(name = "total_photos") val totalPhotos: Int,
    val cameras: List<CameraType>
)
