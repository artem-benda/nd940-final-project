package ru.abenda.marsexplorer.ui.overview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import ru.abenda.marsexplorer.R
import ru.abenda.marsexplorer.data.api.NetworkCallState
import ru.abenda.marsexplorer.data.enums.RoverType
import ru.abenda.marsexplorer.databinding.FragmentOverviewBinding
import timber.log.Timber

@AndroidEntryPoint
abstract class OverviewFragment(
    val roverType: RoverType,
    private val clickNavDirection: NavDirections
) : Fragment() {

    private val viewModel: OverviewViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel.setRoverType(roverType)

        val binding = FragmentOverviewBinding.inflate(layoutInflater, container, false)

        val adapter = OverviewListAdapter(
            OverviewClickListener {
                findNavController().navigate(clickNavDirection)
            }
        )

        viewModel.photosStatsBySol.observe(
            viewLifecycleOwner,
            Observer {
                Timber.d("photosStatsBySol, observed value: %s", it)
                adapter.submitList(it)
            }
        )

        viewModel.refreshState.observe(
            viewLifecycleOwner,
            Observer {
                when (it) {
                    is NetworkCallState.Error<*> -> {
                        Timber.e(it.error)
                        val snackbar = Snackbar.make(binding.root, R.string.error_network_call, Snackbar.LENGTH_INDEFINITE)
                        snackbar.setAction(R.string.action_try_again) {
                            viewModel.launchManifestRefresh(roverType)
                        }
                        snackbar.show()
                    }
                }
            }
        )

        return binding.root
    }
}
