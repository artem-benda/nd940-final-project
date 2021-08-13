package ru.abenda.marsexplorer.data.api

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import ru.abenda.marsexplorer.BuildConfig
import ru.abenda.marsexplorer.data.api.dto.FindPhotosByDateResponse
import ru.abenda.marsexplorer.data.api.dto.GetRoverMissionManifestResponse
import ru.abenda.marsexplorer.data.enums.CameraType
import ru.abenda.marsexplorer.data.enums.RoverType

interface NasaMarsRoverApi {

    @GET("/mars-photos/api/v1/rovers/{rover}/photos")
    suspend fun findPhotosBySol(
        @Path("rover") rover: RoverType,
        @Query("sol") sol: Int,
        @Query("camera") camera: CameraType,
        @Query("page") page: Int,
        @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ): FindPhotosByDateResponse

    @GET("/mars-photos/api/v1/manifests/{rover}")
    suspend fun getMissionManifest(
        @Path("rover") rover: RoverType,
        @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ): GetRoverMissionManifestResponse
}
