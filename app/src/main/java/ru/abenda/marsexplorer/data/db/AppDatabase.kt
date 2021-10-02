package ru.abenda.marsexplorer.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ru.abenda.marsexplorer.data.db.converters.CameraTypesConverter
import ru.abenda.marsexplorer.data.db.dao.RemoteKeysDao
import ru.abenda.marsexplorer.data.db.dao.RoverManifestDao
import ru.abenda.marsexplorer.data.db.dao.RoverPhotosDao
import ru.abenda.marsexplorer.data.db.dao.ThumbnailsDao
import ru.abenda.marsexplorer.data.db.model.PhotosStatsBySol
import ru.abenda.marsexplorer.data.db.model.PhotosStatsBySolThumbnail
import ru.abenda.marsexplorer.data.db.model.RemoteKey
import ru.abenda.marsexplorer.data.db.model.RoverManifest
import ru.abenda.marsexplorer.data.db.model.RoverPhoto

@Database(
    entities = [
        RoverPhoto::class,
        RemoteKey::class,
        RoverManifest::class,
        PhotosStatsBySol::class,
        PhotosStatsBySolThumbnail::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(CameraTypesConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun roverPhotosDao(): RoverPhotosDao
    abstract fun remoteKeysDao(): RemoteKeysDao
    abstract fun roverManifestDao(): RoverManifestDao
    abstract fun thumbnailsDao(): ThumbnailsDao
}
