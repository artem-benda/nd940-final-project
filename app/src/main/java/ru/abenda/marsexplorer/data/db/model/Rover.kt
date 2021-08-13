package ru.abenda.marsexplorer.data.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(tableName = "rovers")
data class Rover(
    @PrimaryKey
    val id: Int,
    val name: String,
    @ColumnInfo(name = "landing_date") val landingDate: LocalDate,
    @ColumnInfo(name = "launch_date") val launchDate: LocalDate,
    val status: String
)
