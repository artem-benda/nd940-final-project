package ru.abenda.marsexplorer.data.api.dto

import com.squareup.moshi.Json

data class RoverManifestDto(
    val name: String,
    @Json(name = "landing_date") val landingDate: String,
    @Json(name = "launch_date") val launchDate: String,
    val status: String,
    @Json(name = "max_sol") val maxSol: Int?,
    @Json(name = "max_date") val maxDate: String,
    @Json(name = "total_photos") val totalPhotos: Int,
    val photos: List<PhotosStatsBySolDto>
)

/*

https://api.nasa.gov/mars-photos/api/v1/manifests/curiosity?api_key=DEMO_KEY

 */
