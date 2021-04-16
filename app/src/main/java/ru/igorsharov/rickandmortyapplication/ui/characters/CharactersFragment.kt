package ru.igorsharov.rickandmortyapplication.ui.characters

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
import ru.igorsharov.rickandmortyapplication.databinding.FragmentCharactersBinding
import ru.igorsharov.rickandmortyapplication.ui.characters.adapter.CharactersPagingAdapter
import javax.inject.Inject

class CharactersFragment : BaseFragment() {

    @Inject
    internal lateinit var charactersAdapter: CharactersPagingAdapter

    private val viewModel: CharactersViewModel by viewModels { viewModelFactory }
    private val binding get() = _binding as FragmentCharactersBinding
    override val bottomNavigationViewVisibility = View.VISIBLE

    override fun getViewBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentCharactersBinding.inflate(inflater, container, false)

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
            rvCharacters.run {
                setHasFixedSize(true)
                setDivider(R.drawable.recycler_view_divider)
                adapter = charactersAdapter.apply {
                    onClickListener = { viewModel.onItemClicked(it) }
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
    }

    override fun initViewModelObservers() {
        viewModel.run {
            observeNotNull(data) { charactersAdapter.submitList(it) }
            observeNotNull(loadState) { binding.swipeContainer.isRefreshing = it }
            observeNotNull(openDetail) {
                findNavController().navigate(CharactersFragmentDirections.toCharacterFragment(it))
            }
        }
    }
}