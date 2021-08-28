package ru.abenda.marsexplorer.data.mapper

import ru.abenda.marsexplorer.data.api.dto.CameraDto
import ru.abenda.marsexplorer.data.api.dto.PhotosStatsBySolDto
import ru.abenda.marsexplorer.data.api.dto.RoverManifestDto
import ru.abenda.marsexplorer.data.api.dto.RoverPhotoDto
import ru.abenda.marsexplorer.data.db.model.PhotosStatsBySol
import ru.abenda.marsexplorer.data.db.model.PhotosStatsBySolThumbnail
import ru.abenda.marsexplorer.data.db.model.RoverManifest
import ru.abenda.marsexplorer.data.db.model.RoverPhoto
import ru.abenda.marsexplorer.data.enums.CameraType
import ru.abenda.marsexplorer.data.enums.RoverType
import java.lang.IllegalArgumentException

fun mapDtoToPhotosStatsBySol(dto: PhotosStatsBySolDto, roverType: RoverType): PhotosStatsBySol {
    with(dto) {
        return PhotosStatsBySol(
            id = computePhotosStatsBySolId(roverType, sol),
            roverType = roverType,
            sol = sol,
            totalPhotos = totalPhotos,
            cameras = cameras
        )
    }
}

fun mapDtoToManifest(dto: RoverManifestDto, roverType: RoverType): RoverManifest {
    with(dto) {
        return RoverManifest(
            roverType = roverType,
            name = name,
            landingDate = landingDate,
            launchDate = launchDate,
            status = status,
            maxSol = maxSol,
            maxDate = maxDate,
            totalPhotos = totalPhotos
        )
    }
}

fun mapDtosToThumbnails(dtos: List<RoverPhotoDto>, roverType: RoverType): List<PhotosStatsBySolThumbnail> {
    return dtos.mapIndexed { index, dto ->
        with(dto) {
            return@mapIndexed PhotosStatsBySolThumbnail(
                statsBySolId = computePhotosStatsBySolId(roverType, sol),
                order = index,
                imageSrc = imageSrc
            )
        }
    }
}

fun mapDtosToPhotos(dtos: List<RoverPhotoDto>, roverType: RoverType): List<RoverPhoto> {
    return dtos.map { dto ->
        with(dto) {
            return@map RoverPhoto(
                id = id,
                sol = sol,
                cameraType = mapDtoToCameraType(camera),
                imageSrc = imageSrc,
                earthDate = earthDate,
                roverType = roverType
            )
        }
    }
}

fun computePhotosStatsBySolId(roverType: RoverType, sol: Int) = "${roverType}_$sol"

fun mapDtoToCameraType(dto: CameraDto): CameraType {
    return try {
        CameraType.valueOf(dto.name)
    } catch (e: IllegalArgumentException) {
        return CameraType.OTHER
    }
}