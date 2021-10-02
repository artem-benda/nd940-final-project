package ru.abenda.marsexplorer.data.repository

import androidx.room.withTransaction
import kotlinx.coroutines.flow.Flow
import ru.abenda.marsexplorer.data.api.NasaMarsRoverApi
import ru.abenda.marsexplorer.data.db.AppDatabase
import ru.abenda.marsexplorer.data.db.model.PhotosStatsBySol
import ru.abenda.marsexplorer.data.db.model.composite.RoverManifestCompositeModel
import ru.abenda.marsexplorer.data.enums.RoverType
import ru.abenda.marsexplorer.data.mapper.computePhotosStatsBySolId
import ru.abenda.marsexplorer.data.mapper.mapDtoToManifest
import ru.abenda.marsexplorer.data.mapper.mapDtoToPhotosStatsBySol
import ru.abenda.marsexplorer.data.mapper.mapDtosToThumbnails
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RoverManifestRepository @Inject constructor(
    private val api: NasaMarsRoverApi,
    private val db: AppDatabase
) {
    suspend fun refreshManifest(roverType: RoverType): Result<Unit> = runCatching {
        val response = api.getMissionManifest(roverType)
        val dto = response.roverManifest
        db.withTransaction {
            db.roverManifestDao().insert(mapDtoToManifest(dto, roverType))
            db.roverManifestDao().clearStatsBySol()
            db.roverManifestDao().insertStatsBySol(dto.photos.map { mapDtoToPhotosStatsBySol(it, roverType) })
        }
    }

    suspend fun countPhotosByManifest(): Long {
        return db.roverManifestDao().count()
    }

    fun getManifestFlow(roverType: RoverType): Flow<RoverManifestCompositeModel> {
        return db.roverManifestDao().getCompositeById(roverType)
    }

    fun getPhotosStatsBySol(roverType: RoverType): Flow<List<PhotosStatsBySol>> {
        return db.roverManifestDao().getStatsByRoverType(roverType)
    }

    suspend fun refreshThumbnailsIfAbsent(roverType: RoverType, sol: Int) {
        val statsBySolId = computePhotosStatsBySolId(roverType, sol)
        val statsBySol = db.roverManifestDao().findStatsById(statsBySolId)
        val thumbnailsLocal = db.thumbnailsDao().findByStatsBySolId(statsBySolId)

        if (statsBySol?.totalPhotos ?: 0 == 0 || thumbnailsLocal.isNotEmpty())
            return

        val photosResult = api.findPhotosBySol(roverType, sol, 1)
        val thumbnails = mapDtosToThumbnails(photosResult.photos, roverType)
        db.thumbnailsDao().insertAll(thumbnails)
    }
}
