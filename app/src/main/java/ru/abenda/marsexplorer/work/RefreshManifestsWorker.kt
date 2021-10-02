package ru.abenda.marsexplorer.work

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import retrofit2.HttpException
import ru.abenda.marsexplorer.R
import ru.abenda.marsexplorer.data.enums.RoverType
import ru.abenda.marsexplorer.data.repository.RoverManifestRepository
import ru.abenda.marsexplorer.notifications.getNotificationManager
import ru.abenda.marsexplorer.notifications.sendNotification
import timber.log.Timber

@HiltWorker
class RefreshManifestsWorker @AssistedInject constructor(
    @Assisted private val appContext: Context,
    @Assisted params: WorkerParameters,
    private val roverManifestRepository: RoverManifestRepository
) : CoroutineWorker(appContext, params) {
    override suspend fun doWork(): Result {
        return try {
            Timber.d("doWork, start")
            val countPhotosBefore = roverManifestRepository.countPhotosByManifest()
            Timber.d("doWork, photos before: %d", countPhotosBefore)

            RoverType.values().forEach {
                roverManifestRepository.refreshManifest(it)
                    .onFailure {
                        throw it
                    }
            }

            val countPhotosAfter = roverManifestRepository.countPhotosByManifest()
            Timber.d("doWork, photos after: %d", countPhotosAfter)

            if (countPhotosAfter > countPhotosBefore) {
                Timber.d("doWork, sending notification...")
                getNotificationManager(appContext)
                    .sendNotification(
                        appContext.getString(R.string.new_photos_template, countPhotosAfter - countPhotosBefore),
                        appContext
                    )
            }
            Timber.d("doWork, success")
            Result.success()
        } catch (e: Exception) {
            if (e is HttpException) {
                Timber.d("doWork, retry")
                Result.retry()
            } else {
                Timber.d("doWork, failure")
                Result.failure()
            }
        }
    }

    companion object {
        const val WORK_NAME = "ru.abenda.marsexplorer.work.RefreshManifestsWorker"
    }
}