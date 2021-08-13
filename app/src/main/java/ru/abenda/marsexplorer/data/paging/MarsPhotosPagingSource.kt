package ru.abenda.marsexplorer.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import retrofit2.HttpException
import ru.abenda.marsexplorer.data.api.NasaMarsRoverApi
import ru.abenda.marsexplorer.data.api.dto.RoverPhotoDto
import ru.abenda.marsexplorer.data.enums.CameraType
import ru.abenda.marsexplorer.data.enums.RoverType
import java.io.IOException

const val STARTING_PAGE_INDEX = 1

class MarsPhotosPagingSource(
    private val api: NasaMarsRoverApi,
    private val roverType: RoverType,
    private val cameraType: CameraType,
    private val sol: Int
) : PagingSource<Int, RoverPhotoDto>() {

    override fun getRefreshKey(state: PagingState<Int, RoverPhotoDto>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, RoverPhotoDto> {
        val position = params.key ?: STARTING_PAGE_INDEX
        return try {
            val response = api.findPhotosBySol(roverType, sol, cameraType, position)
            val repos = response.photos
            val nextKey = if (repos.isEmpty()) {
                null
            } else {
                position + 1
            }
            LoadResult.Page(
                data = repos,
                prevKey = if (position == STARTING_PAGE_INDEX) null else position - 1,
                nextKey = nextKey
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }
}
