package ru.igorsharov.rickandmortyapplication.core.di

import dagger.Component
import ru.igorsharov.rickandmortyapplication.AndroidApplication
import ru.igorsharov.rickandmortyapplication.core.di.module.ApplicationModule
import ru.igorsharov.rickandmortyapplication.core.di.module.viewmodel.ViewModelModule
import ru.igorsharov.rickandmortyapplication.ui.character.CharacterFragment
import ru.igorsharov.rickandmortyapplication.ui.characters.CharactersFragment
import ru.igorsharov.rickandmortyapplication.ui.episode.EpisodeFragment
import ru.igorsharov.rickandmortyapplication.ui.episodes.EpisodesFragment
import ru.igorsharov.rickandmortyapplication.ui.location.LocationFragment
import ru.igorsharov.rickandmortyapplication.ui.locations.LocationsFragment
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, ViewModelModule::class])
interface ApplicationComponent {
    fun inject(application: AndroidApplication)
    fun inject(charactersFragment: CharactersFragment)
    fun inject(characterFragment: CharacterFragment)
    fun inject(locationsFragment: LocationsFragment)
    fun inject(locationFragment: LocationFragment)
    fun inject(episodesFragment: EpisodesFragment)
    fun inject(episodeFragment: EpisodeFragment)
}