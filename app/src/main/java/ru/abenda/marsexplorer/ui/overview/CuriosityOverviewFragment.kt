package ru.abenda.marsexplorer.ui.overview

import dagger.hilt.android.AndroidEntryPoint
import ru.abenda.marsexplorer.data.enums.RoverType

@AndroidEntryPoint
class CuriosityOverviewFragment : OverviewFragment(
    RoverType.Curiosity,
    { sol ->
        CuriosityOverviewFragmentDirections.actionCuriosityOverviewFragmentToPhotosFragment(RoverType.Curiosity, sol)
    }
)
