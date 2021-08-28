package ru.abenda.marsexplorer.data.db.model.composite

import androidx.room.Embedded
import androidx.room.Relation
import ru.abenda.marsexplorer.data.db.model.PhotosStatsBySol
import ru.abenda.marsexplorer.data.db.model.RoverManifest

data class RoverManifestCompositeModel(
    @Embedded val roverManifest: RoverManifest,

    @Relation(
        parentColumn = "rover_type",
        entityColumn = "rover_type",
        entity = PhotosStatsBySol::class
    )
    val photosStatsBySol: List<PhotosStatsBySolWithThumbnails>
)
