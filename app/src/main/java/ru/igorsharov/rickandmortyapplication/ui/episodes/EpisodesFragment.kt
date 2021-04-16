package ru.igorsharov.rickandmortyapplication.ui.episodes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import ru.igorsharov.rickandmortyapplication.R
import ru.igorsharov.rickandmortyapplication.core.extension.observeNotNull
import ru.igorsharov.rickandmortyapplication.core.extension.setDivider
import ru.igorsharov.rickandmortyapplication.core.platform.BaseFragment
import ru.igorsharov.rickandmortyapplication.databinding.FragmentEpisodesBinding
import ru.igorsharov.rickandmortyapplication.ui.episodes.adapter.EpisodesPagingAdapter
import javax.inject.Inject

class EpisodesFragment : BaseFragment() {

    @Inject
    internal lateinit var episodesAdapter: EpisodesPagingAdapter

    private val viewModel: EpisodesViewModel by viewModels { viewModelFactory }
    private val binding get() = _binding!! as FragmentEpisodesBinding
    override val bottomNavigationViewVisibility = View.VISIBLE

    override fun getViewBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentEpisodesBinding.inflate(inflater, container, false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
    }

    private fun initAdapter() {
        with(binding) {
            rvEpisodes.run {
                setHasFixedSize(true)
                setDivider(R.drawable.recycler_view_divider)
                adapter = episodesAdapter.apply {
                    onClickListener = { viewModel.onItemClicked(it) }
                }
            }

            swipeContainer.setOnRefreshListener {
                viewModel.onRefresh()
            }

            swipeContainer.setColorSchemeResources(
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_blue_dark
            )
        }
    }

    override fun initViewModelObservers() {
        viewModel.run {
            observeNotNull(data) { episodesAdapter.submitList(it) }
            observeNotNull(loadState) { binding.swipeContainer.isRefreshing = it }
            observeNotNull(openDetail) {
                findNavController().navigate(EpisodesFragmentDirections.toEpisodeFragment(it))
            }
        }
    }
}