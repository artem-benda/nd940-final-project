package ru.abenda.marsexplorer.data.api.dto

import com.squareup.moshi.Json
import java.time.LocalDate

data class RoverManifestDto(
    val name: String,
    @Json(name = "landing_date") val landingDate: LocalDate,
    @Json(name = "launch_date") val launchDate: LocalDate,
    val status: String,
    @Json(name = "max_sol") val maxSol: Int?,
    @Json(name = "max_date") val maxDate: LocalDate,
    @Json(name = "total_photos") val totalPhotos: Int,
    val photos: List<PhotosStatsBySolDto>
)

/*

https://api.nasa.gov/mars-photos/api/v1/manifests/curiosity?api_key=DEMO_KEY

 */
