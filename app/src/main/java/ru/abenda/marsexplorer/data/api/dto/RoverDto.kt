package ru.abenda.marsexplorer.data.api.dto

import com.squareup.moshi.Json

data class RoverDto(
    val id: Int,
    val name: String,
    @Json(name = "landing_date") val landingDate: String,
    @Json(name = "launch_date") val launchDate: String,
    val status: String
)
