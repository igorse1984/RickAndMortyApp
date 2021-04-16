package ru.igorsharov.rickandmortyapplication.core.di.module.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ru.igorsharov.rickandmortyapplication.ui.character.CharacterViewModel
import ru.igorsharov.rickandmortyapplication.ui.characters.CharactersViewModel
import ru.igorsharov.rickandmortyapplication.ui.episode.EpisodeViewModel
import ru.igorsharov.rickandmortyapplication.ui.episodes.EpisodesViewModel
import ru.igorsharov.rickandmortyapplication.ui.location.LocationViewModel
import ru.igorsharov.rickandmortyapplication.ui.locations.LocationsViewModel

@Module
abstract class ViewModelModule {
    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(CharactersViewModel::class)
    abstract fun bindCharactersViewModel(viewModel: CharactersViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CharacterViewModel::class)
    abstract fun bindCharacterViewModel(viewModel: CharacterViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(LocationsViewModel::class)
    abstract fun bindLocationsViewModel(viewModel: LocationsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(LocationViewModel::class)
    abstract fun bindLocationViewModel(viewModel: LocationViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(EpisodesViewModel::class)
    abstract fun bindEpisodesViewModel(viewModel: EpisodesViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(EpisodeViewModel::class)
    abstract fun bindEpisodeViewModel(viewModel: EpisodeViewModel): ViewModel
}