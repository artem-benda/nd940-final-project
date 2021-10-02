package ru.abenda.marsexplorer.ui.photos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import ru.abenda.marsexplorer.R
import ru.abenda.marsexplorer.databinding.PhotosFragmentBinding

@AndroidEntryPoint
class PhotosFragment : Fragment() {

    private val viewModel: PhotosViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val args = PhotosFragmentArgs.fromBundle(requireArguments())

        (requireActivity() as? AppCompatActivity)?.supportActionBar?.title =
            getString(R.string.photos_fragment_title_template, args.roverType, args.sol)

        val photosAdapter = PagingPhotosAdapter(
            PhotoListItemListener {
                findNavController().navigate(PhotosFragmentDirections.actionPhotosFragmentToPhotoFragment(it))
            }
        )
        val header = PhotosLoadingStateAdapter { photosAdapter.retry() }
        photosAdapter.withLoadStateHeaderAndFooter(header, header)

        val binding = PhotosFragmentBinding.inflate(layoutInflater, container, false)
        binding.lifecycleOwner = this
        binding.list.adapter = photosAdapter

        lifecycleScope.launch {
            viewModel.fetchPhotos(args.roverType, args.sol).collectLatest { pagingData ->
                photosAdapter.submitData(pagingData)
            }
        }

        lifecycleScope.launch {
            photosAdapter.loadStateFlow.collectLatest { loadState ->
                header.loadState = loadState.mediator
                    ?.refresh
                    ?.takeIf { it is LoadState.Error && photosAdapter.itemCount > 0 }
                    ?: loadState.prepend

                val isListEmpty = loadState.refresh is LoadState.NotLoading && photosAdapter.itemCount == 0
                binding.emptyList.isVisible = isListEmpty
                binding.list.isVisible = loadState.source.refresh is LoadState.NotLoading || loadState.mediator?.refresh is LoadState.NotLoading
                binding.progressBar.isVisible = loadState.mediator?.refresh is LoadState.Loading
                binding.retryButton.isVisible = loadState.mediator?.refresh is LoadState.Error && photosAdapter.itemCount == 0
                val errorState = loadState.source.append as? LoadState.Error
                    ?: loadState.source.prepend as? LoadState.Error
                    ?: loadState.append as? LoadState.Error
                    ?: loadState.prepend as? LoadState.Error
                errorState?.let {
                    Toast.makeText(
                        this@PhotosFragment.context,
                        "An error occurred: ${it.error}",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }

        return binding.root
    }
}
