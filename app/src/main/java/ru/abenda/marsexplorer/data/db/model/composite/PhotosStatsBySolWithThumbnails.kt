package ru.abenda.marsexplorer.data.db.model.composite

import androidx.room.Embedded
import androidx.room.Relation
import ru.abenda.marsexplorer.data.db.model.PhotosStatsBySol
import ru.abenda.marsexplorer.data.db.model.PhotosStatsBySolThumbnail

data class PhotosStatsBySolWithThumbnails(
    @Embedded val photosStatsBySol: PhotosStatsBySol,

    @Relation(
        parentColumn = "id",
        entityColumn = "stats_by_sol_id"
    )
    val thumbnails: List<PhotosStatsBySolThumbnail>
)
