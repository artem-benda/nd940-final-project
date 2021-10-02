package ru.abenda.marsexplorer.ui.photo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import ru.abenda.marsexplorer.databinding.PhotoFragmentBinding

@AndroidEntryPoint
class PhotoFragment : Fragment() {

    companion object {
        fun newInstance() = PhotoFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val args = PhotoFragmentArgs.fromBundle(requireArguments())
        val binding = PhotoFragmentBinding.inflate(layoutInflater, container, false)
        binding.lifecycleOwner = this

        binding.photo = args.photo

        return binding.root
    }
}
