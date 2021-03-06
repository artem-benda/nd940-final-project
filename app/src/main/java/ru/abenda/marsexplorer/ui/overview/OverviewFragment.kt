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
import androidx.recyclerview.widget.DividerItemDecoration
import ru.abenda.marsexplorer.notifications.createChannel

@AndroidEntryPoint
abstract class OverviewFragment(
    val roverType: RoverType,
    private val clickNavDirection: (sol: Int) -> NavDirections
) : Fragment() {

    private val viewModel: OverviewViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel.setRoverType(roverType)

        val binding = FragmentOverviewBinding.inflate(layoutInflater, container, false)
        binding.lifecycleOwner = this

        binding.viewModel = viewModel

        val adapter = OverviewListAdapter(
            OverviewListItemListener(
                {
                    findNavController().navigate(clickNavDirection(it.photosStatsBySol.sol))
                },
                {
                    viewModel.launchLoadThumbnails(it.photosStatsBySol.roverType, it.photosStatsBySol.sol)
                }
            )
        )
        binding.overviewList.adapter = adapter

        val dividerItemDecoration = DividerItemDecoration(
            binding.overviewList.context,
            DividerItemDecoration.VERTICAL
        )
        binding.overviewList.addItemDecoration(dividerItemDecoration)

        viewModel.manifest.observe(
            viewLifecycleOwner,
            Observer {
                Timber.d("manifest, observed value: %s", it)
                it?.let {
                    adapter.submitList(it.photosStatsBySol)
                }
            }
        )

        viewModel.refreshState.observe(
            viewLifecycleOwner,
            Observer {
                Timber.i("refreshState is %s", it)
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

        createChannel(
            requireActivity().applicationContext,
            getString(R.string.new_photos_notification_channel_id),
            getString(R.string.new_photos_notification_channel_name)
        )

        return binding.root
    }
}
