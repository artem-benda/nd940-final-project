package ru.abenda.marsexplorer.work

import android.content.Context
import android.os.Build
import androidx.work.*
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WorkManagerUtils @Inject constructor() {

    fun enqueueAll(context: Context) {
        enqueueRefreshRoverPhotosRequest(context)
    }

    private fun enqueueRefreshRoverPhotosRequest(context: Context) {
        Timber.d("enqueueRefreshRoverPhotosRequest...")
        val constraints = buildDefaultConstraints()

        val repeatingRefreshRoverManifestsRequest = PeriodicWorkRequestBuilder<RefreshManifestsWorker>(1, TimeUnit.DAYS)
            .setConstraints(constraints)
            .build()

        WorkManager.getInstance(context).enqueueUniquePeriodicWork(
            RefreshManifestsWorker.WORK_NAME,
            ExistingPeriodicWorkPolicy.KEEP,
            repeatingRefreshRoverManifestsRequest
        )
    }

    private fun buildDefaultConstraints() = Constraints.Builder()
        .setRequiredNetworkType(NetworkType.UNMETERED)
        .setRequiresBatteryNotLow(true)
        .setRequiresCharging(true)
        .apply {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                setRequiresDeviceIdle(true)
            }
        }
        .build()
}
