package ru.igorsharov.rickandmortyapplication.ui.locations

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import ru.igorsharov.rickandmortyapplication.R
import ru.igorsharov.rickandmortyapplication.core.extension.observeNotNull
import ru.igorsharov.rickandmortyapplication.core.extension.setDivider
import ru.igorsharov.rickandmortyapplication.core.platform.BaseFragment
import ru.igorsharov.rickandmortyapplication.core.utils.PaginationScrollListener
import ru.igorsharov.rickandmortyapplication.databinding.FragmentLocationsBinding
import ru.igorsharov.rickandmortyapplication.ui.locations.adapter.LocationsAdapter
import javax.inject.Inject

class LocationsFragment : BaseFragment() {

    @Inject
    internal lateinit var locationsAdapter: LocationsAdapter
    private lateinit var scrollListener: PaginationScrollListener

    private val viewModel: LocationsViewModel by viewModels { viewModelFactory }
    private val binding get() = _binding!! as FragmentLocationsBinding
    override val bottomNavigationViewVisibility = View.VISIBLE

    override fun getViewBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentLocationsBinding.inflate(inflater, container, false)

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
            rvLocations.run {
                setHasFixedSize(true)

                val layoutManager = layoutManager!! as LinearLayoutManager
                scrollListener = object : PaginationScrollListener(layoutManager) {
                    override fun loadNext() {
                        viewModel.onLoadNext()
                    }
                }

                addOnScrollListener(scrollListener)
                setDivider(R.drawable.recycler_view_divider)
                adapter = locationsAdapter.apply {
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
            observeNotNull(initData) { locationsAdapter.collection = it }
            observeNotNull(loadState) {
                binding.swipeContainer.isRefreshing = it
                scrollListener.isLoading = it
            }
            observeNotNull(openDetail) {
                findNavController().navigate(LocationsFragmentDirections.toLocationFragment(it))
            }
        }
    }
}