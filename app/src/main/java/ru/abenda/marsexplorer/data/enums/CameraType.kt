package ru.abenda.marsexplorer.data.enums

import androidx.annotation.StringRes
import ru.abenda.marsexplorer.R

enum class CameraType private constructor(@StringRes val cameraNameResId: Int) {
    FHAZ(R.string.FHAZ),
    RHAZ(R.string.RHAZ),
    MAST(R.string.MAST),
    CHEMCAM(R.string.CHEMCAM),
    MAHLI(R.string.MAHLI),
    MARDI(R.string.MARDI),
    NAVCAM(R.string.NAVCAM),
    PANCAM(R.string.PANCAM),
    MINITES(R.string.MINITES),
    OTHER(R.string.OTHER)
}
