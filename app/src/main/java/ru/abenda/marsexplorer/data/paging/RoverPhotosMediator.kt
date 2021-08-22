package ru.abenda.marsexplorer.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import retrofit2.HttpException
import ru.abenda.marsexplorer.data.api.NasaMarsRoverApi
import ru.abenda.marsexplorer.data.enums.CameraType
import ru.abenda.marsexplorer.data.enums.RoverType
import ru.abenda.marsexplorer.data.db.AppDatabase
import ru.abenda.marsexplorer.data.db.model.RemoteKey
import ru.abenda.marsexplorer.data.db.model.RoverPhoto
import java.io.IOException

@ExperimentalPagingApi
class RoverPhotosMediator(
    private val api: NasaMarsRoverApi,
    private val db: AppDatabase,
    private val roverType: RoverType,
    private val cameraType: CameraType,
    private val sol: Int
) : RemoteMediator<Int, RoverPhoto>() {

    override suspend fun initialize(): InitializeAction {
        return InitializeAction.LAUNCH_INITIAL_REFRESH
    }

    override suspend fun load(loadType: LoadType, state: PagingState<Int, RoverPhoto>): MediatorResult {

        val page = when (loadType) {
            LoadType.REFRESH -> {
                val remoteKey = getRemoteKeyClosestToCurrentPosition(state)
                remoteKey?.nextKey?.minus(1) ?: 1
            }
            LoadType.PREPEND -> {
                val remoteKey = getRemoteKeyForFirstItem(state)
                val prevKey = remoteKey?.prevKey
                if (prevKey == null) {
                    return MediatorResult.Success(endOfPaginationReached = remoteKey != null)
                }
                prevKey
            }
            LoadType.APPEND -> {
                val remoteKey = getRemoteKeyForLastItem(state)
                val nextKey = remoteKey?.nextKey
                if (nextKey == null) {
                    return MediatorResult.Success(endOfPaginationReached = remoteKey != null)
                }
                nextKey
            }
        }

        try {
            val apiResponse = api.findPhotosBySol(roverType, sol, cameraType, page)

            val photos = apiResponse.photos
            val endOfPaginationReached = photos.isEmpty()
            db.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    db.remoteKeysDao().clear()
                    db.roverPhotosDao().clear()
                }
                val prevKey = if (page == 1) null else page - 1
                val nextKey = if (endOfPaginationReached) null else page + 1
                val keyModels = photos.map {
                    RemoteKey(photoId = it.id, prevKey = prevKey, nextKey = nextKey)
                }
                val photoModels = photos.map {
                    RoverPhoto(it.id, it.sol, cameraType, it.imageSrc, it.earthDate, roverType)
                }
                db.remoteKeysDao().insertAll(keyModels)
                db.roverPhotosDao().insertAll(photoModels)
            }
            return MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (exception: IOException) {
            return MediatorResult.Error(exception)
        } catch (exception: HttpException) {
            return MediatorResult.Error(exception)
        }
    }

    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, RoverPhoto>): RemoteKey? {
        return state.pages.lastOrNull() { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { photo ->
                db.remoteKeysDao().remoteKeysPhotoId(photo.id)
            }
    }

    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, RoverPhoto>): RemoteKey? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { photo ->
                db.remoteKeysDao().remoteKeysPhotoId(photo.id)
            }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, RoverPhoto>
    ): RemoteKey? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { photoId ->
                db.remoteKeysDao().remoteKeysPhotoId(photoId)
            }
        }
    }
}