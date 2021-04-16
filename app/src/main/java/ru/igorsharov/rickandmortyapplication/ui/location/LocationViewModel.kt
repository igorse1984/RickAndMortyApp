package ru.igorsharov.rickandmortyapplication.ui.location

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.igorsharov.rickandmortyapplication.core.api.ApiRepository
import ru.igorsharov.rickandmortyapplication.core.platform.BaseViewModel
import ru.igorsharov.rickandmortyapplication.ui.entities.LocationView
import ru.igorsharov.rickandmortyapplication.ui.entities.mapToView
import javax.inject.Inject

class LocationViewModel @Inject constructor(
    private val apiRepository: ApiRepository
) : BaseViewModel() {

    private val _initData = MutableLiveData<LocationView>().apply { value = LocationView.empty() }
    val initData: LiveData<LocationView> = _initData

    fun onViewCreated(characterId: Int) {
        loadApi(characterId)
    }

    private fun loadApi(characterId: Int) {
        compositeDisposable.add(
            apiRepository.getLocation(characterId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError { showError(it) }
                .subscribe { location -> _initData.value = location.mapToView() })
    }
}