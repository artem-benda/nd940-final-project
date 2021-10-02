package ru.abenda.marsexplorer.data.api.dto

import com.squareup.moshi.Json

data class CameraDto(
    val id: Int,
    val name: String,
    @Json(name = "rover_id") val roverId: Int,
    @Json(name = "full_name") val fullName: String
)
