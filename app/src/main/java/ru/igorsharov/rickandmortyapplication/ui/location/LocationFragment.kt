package ru.igorsharov.rickandmortyapplication.ui.location

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import ru.igorsharov.rickandmortyapplication.R
import ru.igorsharov.rickandmortyapplication.core.extension.observeNotNull
import ru.igorsharov.rickandmortyapplication.core.platform.ActionBarActivity
import ru.igorsharov.rickandmortyapplication.core.platform.BaseFragment
import ru.igorsharov.rickandmortyapplication.databinding.FragmentLocationBinding

class LocationFragment : BaseFragment() {

    private val viewModel: LocationViewModel by viewModels { viewModelFactory }
    private val binding get() = _binding as FragmentLocationBinding
    private val safeArgs: LocationFragmentArgs by navArgs()

    override fun getViewBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentLocationBinding.inflate(inflater, container, false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
        (requireActivity() as ActionBarActivity).setActionBarTitle(R.string.title_location)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.onViewCreated(safeArgs.id)
    }

    override fun initViewModelObservers() {
        viewModel.run {
            observeNotNull(initData) {
                with(binding.includeLocation) {
                    tvName.text = it.name
                    tvType.text = it.type
                    tvDimension.text = it.dimension
                }
            }
        }
    }
}