package ru.abenda.marsexplorer.data.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(
    tableName = "photos_stats_by_sol_thumbnails",
    primaryKeys = ["stats_by_sol_id", "order"]
)
data class PhotosStatsBySolThumbnail(
    @ColumnInfo(name = "stats_by_sol_id") val statsBySolId: String,
    val order: Int,
    @ColumnInfo(name = "img_src") val imageSrc: String
)
