package ru.igorsharov.rickandmortyapplication

import android.app.Application
import ru.igorsharov.rickandmortyapplication.core.di.ApplicationComponent
import ru.igorsharov.rickandmortyapplication.core.di.DaggerApplicationComponent
import ru.igorsharov.rickandmortyapplication.core.di.module.ApplicationModule

class AndroidApplication : Application() {

    val appComponent: ApplicationComponent by lazy(mode = LazyThreadSafetyMode.NONE) {
        DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule())
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        appComponent.inject(this)
    }
}
