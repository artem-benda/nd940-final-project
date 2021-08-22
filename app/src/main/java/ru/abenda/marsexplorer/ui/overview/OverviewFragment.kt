package ru.abenda.marsexplorer.ui.overview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import ru.abenda.marsexplorer.data.enums.RoverType
import ru.abenda.marsexplorer.databinding.FragmentOverviewBinding

abstract class OverviewFragment(val roverType: RoverType) : Fragment() {

    private val viewModel: OverviewViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentOverviewBinding.inflate(layoutInflater, container, false)
        return binding.root
    }
}
