package ru.abenda.marsexplorer.data.api.dto

import com.squareup.moshi.Json

data class RoverPhotoDto(
    val id: Long,
    val sol: Int,
    val camera: CameraDto,
    @Json(name = "img_src") val imageSrc: String,
    @Json(name = "earth_date") val earthDate: String,
    val rover: RoverDto
)
