package ru.abenda.marsexplorer.data.api.dto

import com.squareup.moshi.Json

data class GetRoverMissionManifestResponse(
    @Json(name = "photo_manifest") val roverManifest: RoverManifestDto
)
