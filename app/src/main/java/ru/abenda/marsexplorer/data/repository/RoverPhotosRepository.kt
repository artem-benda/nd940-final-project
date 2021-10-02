package ru.abenda.marsexplorer.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import ru.abenda.marsexplorer.data.api.NasaMarsRoverApi
import ru.abenda.marsexplorer.data.db.AppDatabase
import ru.abenda.marsexplorer.data.db.model.RoverPhoto
import ru.abenda.marsexplorer.data.enums.CameraType
import ru.abenda.marsexplorer.data.enums.RoverType
import ru.abenda.marsexplorer.data.paging.RoverPhotosMediator
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RoverPhotosRepository @Inject constructor(
    private val api: NasaMarsRoverApi,
    private val db: AppDatabase
) {

    fun getSearchResultStream(
        roverType: RoverType,
        cameraType: CameraType?,
        sol: Int
    ): Flow<PagingData<RoverPhoto>> {
        val pagingSourceFactory = { db.roverPhotosDao().findPhotos(roverType, cameraType, sol) }

        @OptIn(ExperimentalPagingApi::class)
        return Pager(
            config = PagingConfig(pageSize = NETWORK_PAGE_SIZE, enablePlaceholders = false),
            remoteMediator = RoverPhotosMediator(
                api,
                db,
                roverType,
                cameraType,
                sol
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }

    companion object {
        const val NETWORK_PAGE_SIZE = 25
    }
}
