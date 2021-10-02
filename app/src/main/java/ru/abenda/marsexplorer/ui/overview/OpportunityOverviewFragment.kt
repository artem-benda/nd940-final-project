package ru.abenda.marsexplorer.ui.overview

import dagger.hilt.android.AndroidEntryPoint
import ru.abenda.marsexplorer.data.enums.RoverType

@AndroidEntryPoint
class OpportunityOverviewFragment : OverviewFragment(
    RoverType.Opportunity,
    { sol ->
        OpportunityOverviewFragmentDirections.actionOpportunityOverviewFragmentToPhotosFragment(RoverType.Opportunity, sol)
    }
)