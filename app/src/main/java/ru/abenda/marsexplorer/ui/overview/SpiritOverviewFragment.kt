package ru.abenda.marsexplorer.ui.overview

import dagger.hilt.android.AndroidEntryPoint
import ru.abenda.marsexplorer.data.enums.RoverType

@AndroidEntryPoint
class SpiritOverviewFragment : OverviewFragment(
    RoverType.Spirit,
    { sol ->
        SpiritOverviewFragmentDirections.actionSpiritOverviewFragmentToPhotosFragment(RoverType.Spirit, sol)
    }
)