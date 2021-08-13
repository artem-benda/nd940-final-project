package ru.abenda.marsexplorer.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import ru.abenda.marsexplorer.data.api.NasaMarsRoverApi
import ru.abenda.marsexplorer.data.api.dto.RoverPhotoDto
import ru.abenda.marsexplorer.data.enums.CameraType
import ru.abenda.marsexplorer.data.enums.RoverType
import ru.abenda.marsexplorer.data.paging.MarsPhotosPagingSource
import javax.inject.Inject

class NasaMarsRoverRepository @Inject constructor(private val api: NasaMarsRoverApi) {

    fun getSearchResultStream(
        roverType: RoverType,
        cameraType: CameraType,
        sol: Int
    ): Flow<PagingData<RoverPhotoDto>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { MarsPhotosPagingSource(api, roverType, cameraType, sol) }
        ).flow
    }

    companion object {
        const val NETWORK_PAGE_SIZE = 25
    }
}
