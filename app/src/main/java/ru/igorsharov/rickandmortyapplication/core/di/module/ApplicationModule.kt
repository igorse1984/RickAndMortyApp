package ru.igorsharov.rickandmortyapplication.core.di.module

import dagger.Module
import dagger.Provides
import ru.igorsharov.rickandmortyapplication.core.api.ApiRepository
import javax.inject.Singleton

@Module(includes = [RetrofitModule::class])
class ApplicationModule {

    @Provides
    @Singleton
    fun provideApiRepository(apiRepository: ApiRepository.ApiRepositoryImpl): ApiRepository =
        apiRepository
}