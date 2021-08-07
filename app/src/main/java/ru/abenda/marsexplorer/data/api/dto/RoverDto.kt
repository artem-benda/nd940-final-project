package ru.abenda.marsexplorer.data.api.dto

import com.squareup.moshi.Json
import java.time.LocalDate

data class RoverDto(
    val id: Int,
    val name: String,
    @Json(name = "landing_date") val landingDate: LocalDate,
    @Json(name = "launch_date") val launchDate: LocalDate,
    val status: String
)
