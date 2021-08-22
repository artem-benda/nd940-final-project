package ru.abenda.marsexplorer.data.mapper

import ru.abenda.marsexplorer.data.api.dto.PhotosStatsBySolDto
import ru.abenda.marsexplorer.data.api.dto.RoverManifestDto
import ru.abenda.marsexplorer.data.db.model.PhotosStatsBySol
import ru.abenda.marsexplorer.data.db.model.RoverManifest
import ru.abenda.marsexplorer.data.enums.RoverType

fun mapDtoToPhotosStatsBySol(dto: PhotosStatsBySolDto, roverType: RoverType): PhotosStatsBySol {
    return PhotosStatsBySol(
        roverType = roverType,
        sol = dto.sol,
        totalPhotos = dto.totalPhotos,
        cameras = dto.cameras
    )
}

fun mapDtoToManifest(dto: RoverManifestDto, roverType: RoverType): RoverManifest {
    return RoverManifest(
        roverType = roverType,
        name = dto.name,
        landingDate = dto.landingDate,
        launchDate = dto.launchDate,
        status = dto.status,
        maxSol = dto.maxSol,
        maxDate = dto.maxDate,
        totalPhotos = dto.totalPhotos
    )
}
