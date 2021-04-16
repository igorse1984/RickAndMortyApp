package ru.igorsharov.rickandmortyapplication.ui.locations

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.igorsharov.rickandmortyapplication.core.api.ApiRepository
import ru.igorsharov.rickandmortyapplication.core.api.entities.LocationResponse
import ru.igorsharov.rickandmortyapplication.core.api.entities.LocationsResponse
import ru.igorsharov.rickandmortyapplication.core.platform.BaseViewModel
import ru.igorsharov.rickandmortyapplication.core.platform.SingleLiveEvent
import ru.igorsharov.rickandmortyapplication.ui.entities.LocationView
import ru.igorsharov.rickandmortyapplication.ui.entities.mapToView
import javax.inject.Inject

class LocationsViewModel @Inject constructor(
    private val apiRepository: ApiRepository
) : BaseViewModel() {

    private var results = mutableListOf<LocationResponse>()
    private var nexUrl: String? = null

    private val _initData = MutableLiveData<List<LocationView>>().apply { value = emptyList() }
    val initData: LiveData<List<LocationView>> = _initData

    private val _loadState = MutableLiveData<Boolean>()
    val loadState: LiveData<Boolean> = _loadState

    private val _openDetail = SingleLiveEvent<Int>()
    val openDetail: LiveData<Int> = _openDetail

    init {
        loadApi()
    }

    private fun loadApi() {
        compositeDisposable.add(
            apiRepository.getLocations(1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { _loadState.value = true }
                .doFinally { _loadState.value = false }
                .doOnError { showError(it) }
                .subscribe { response -> handleResponse(response) })
    }

    fun onLoadNext() {
        compositeDisposable.add(
            apiRepository.getLocations(nexUrl ?: return)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { _loadState.value = true }
                .doFinally { _loadState.value = false }
                .doOnError { showError(it) }
                .subscribe { response -> handleResponse(response) })
    }

    fun onRefresh() {
        loadApi()
//        _loadState.value = false
    }

    private fun handleResponse(response: LocationsResponse) {
        results.addAll(response.results)
        nexUrl = response.info.next
        _initData.value = results.map { it.mapToView() }
    }

    fun onItemClicked(position: Int) {
        _openDetail.value = results[position].id
    }
}