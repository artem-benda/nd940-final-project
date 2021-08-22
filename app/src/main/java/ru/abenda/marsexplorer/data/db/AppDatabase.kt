package ru.abenda.marsexplorer.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.abenda.marsexplorer.data.db.dao.RemoteKeysDao
import ru.abenda.marsexplorer.data.db.dao.RoverManifestDao
import ru.abenda.marsexplorer.data.db.dao.RoverPhotosDao
import ru.abenda.marsexplorer.data.db.model.PhotosStatsBySol
import ru.abenda.marsexplorer.data.db.model.RemoteKey
import ru.abenda.marsexplorer.data.db.model.RoverManifest
import ru.abenda.marsexplorer.data.db.model.RoverPhoto

@Database(
    entities = [
        RoverPhoto::class,
        RemoteKey::class,
        RoverManifest::class,
        PhotosStatsBySol::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun roverPhotosDao(): RoverPhotosDao
    abstract fun remoteKeysDao(): RemoteKeysDao
    abstract fun roverManifestDao(): RoverManifestDao
}