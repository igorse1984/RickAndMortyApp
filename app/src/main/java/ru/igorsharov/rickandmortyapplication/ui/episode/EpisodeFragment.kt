package ru.igorsharov.rickandmortyapplication.ui.episode

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
import ru.igorsharov.rickandmortyapplication.databinding.FragmentEpisodeBinding

class EpisodeFragment : BaseFragment() {

    private val viewModel: EpisodeViewModel by viewModels { viewModelFactory }
    private val binding get() = _binding as FragmentEpisodeBinding
    private val safeArgs: EpisodeFragmentArgs by navArgs()

    override fun getViewBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentEpisodeBinding.inflate(inflater, container, false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
        (requireActivity() as ActionBarActivity).setActionBarTitle(R.string.title_episode)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.onViewCreated(safeArgs.id)
    }

    override fun initViewModelObservers() {
        viewModel.run {
            observeNotNull(initData) {
                with(binding.includeEpisode) {
                    tvName.text = it.name
                    tvAirDate.text = it.airDate
                    tvEpisode.text = it.episode
                }
            }
        }
    }
}