package ru.abenda.marsexplorer.data.repository

import androidx.room.withTransaction
import kotlinx.coroutines.flow.Flow
import ru.abenda.marsexplorer.data.api.NasaMarsRoverApi
import ru.abenda.marsexplorer.data.db.AppDatabase
import ru.abenda.marsexplorer.data.db.model.PhotosStatsBySol
import ru.abenda.marsexplorer.data.db.model.RoverManifest
import ru.abenda.marsexplorer.data.enums.RoverType
import ru.abenda.marsexplorer.data.mapper.mapDtoToManifest
import ru.abenda.marsexplorer.data.mapper.mapDtoToPhotosStatsBySol
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

    fun getManifestFlow(roverType: RoverType): Flow<RoverManifest> {
        return db.roverManifestDao().getById(roverType)
    }

    fun getPhotosStatsBySol(roverType: RoverType): Flow<List<PhotosStatsBySol>> {
        return db.roverManifestDao().getStatsByRoverType(roverType)
    }
}
