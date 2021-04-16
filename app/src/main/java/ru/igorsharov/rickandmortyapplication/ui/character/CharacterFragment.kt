package ru.igorsharov.rickandmortyapplication.ui.character

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import ru.igorsharov.rickandmortyapplication.R
import ru.igorsharov.rickandmortyapplication.core.extension.loadToSquareFromUrl
import ru.igorsharov.rickandmortyapplication.core.extension.observeNotNull
import ru.igorsharov.rickandmortyapplication.core.platform.ActionBarActivity
import ru.igorsharov.rickandmortyapplication.core.platform.BaseFragment
import ru.igorsharov.rickandmortyapplication.databinding.FragmentCharacterBinding

class CharacterFragment : BaseFragment() {

    private val viewModel: CharacterViewModel by viewModels { viewModelFactory }
    private val binding get() = _binding as FragmentCharacterBinding
    private val safeArgs: CharacterFragmentArgs by navArgs()

    override fun getViewBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentCharacterBinding.inflate(inflater, container, false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
        (requireActivity() as ActionBarActivity).setActionBarTitle(R.string.title_character)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.onViewCreated(safeArgs.id)
    }

    override fun initViewModelObservers() {
        viewModel.run {
            observeNotNull(initData) {
                with(binding) {
                    tvLocation.text = it.location
                    tvName.text = it.name
                    tvGender.text = it.gender
                    tvSpecies.text = it.species
                    tvStatus.text = it.status
                    ivImage.loadToSquareFromUrl(it.image)
                }
            }
        }
    }
}