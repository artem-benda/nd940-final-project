package ru.abenda.marsexplorer.data.api.dto

import com.squareup.moshi.Json
import java.time.LocalDate

data class RoverPhotoDto(
    val id: Long,
    val sol: Int,
    val camera: CameraDto,
    @Json(name = "img_src") val imageSrc: String,
    @Json(name = "earth_date") val earthDate: LocalDate,
    val rover: RoverDto
)
