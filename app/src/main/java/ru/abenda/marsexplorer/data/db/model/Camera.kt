package ru.abenda.marsexplorer.data.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cameras")
data class Camera(
    @PrimaryKey
    val id: Int,
    val name: String,
    @ColumnInfo(name = "rover_id") val roverId: Int,
    @ColumnInfo(name = "full_name") val fullName: String
)
