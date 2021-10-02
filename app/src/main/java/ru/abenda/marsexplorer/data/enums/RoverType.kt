package ru.abenda.marsexplorer.data.enums

enum class RoverType private constructor(val cameraTypes: Set<CameraType>) {
    Curiosity(
        setOf(
            CameraType.FHAZ,
            CameraType.RHAZ,
            CameraType.MAST,
            CameraType.CHEMCAM,
            CameraType.MAHLI,
            CameraType.MARDI,
            CameraType.NAVCAM
        )
    ),
    Opportunity(
        setOf(
            CameraType.FHAZ,
            CameraType.RHAZ,
            CameraType.NAVCAM,
            CameraType.PANCAM,
            CameraType.MINITES
        )
    ),
    Spirit(
        setOf(
            CameraType.FHAZ,
            CameraType.RHAZ,
            CameraType.NAVCAM,
            CameraType.PANCAM,
            CameraType.MINITES
        )
    )
}
