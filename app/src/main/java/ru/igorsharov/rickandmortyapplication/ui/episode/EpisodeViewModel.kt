package ru.igorsharov.rickandmortyapplication.ui.episode

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.igorsharov.rickandmortyapplication.core.api.ApiRepository
import ru.igorsharov.rickandmortyapplication.core.platform.BaseViewModel
import ru.igorsharov.rickandmortyapplication.ui.entities.EpisodeView
import ru.igorsharov.rickandmortyapplication.ui.entities.mapToView
import javax.inject.Inject

class EpisodeViewModel @Inject constructor(
    private val apiRepository: ApiRepository
) : BaseViewModel() {
    private val _initData = MutableLiveData<EpisodeView>().apply { value = EpisodeView.empty() }
    val initData: LiveData<EpisodeView> = _initData

    fun onViewCreated(characterId: Int) {
        loadApi(characterId)
    }

    private fun loadApi(characterId: Int) {
        compositeDisposable.add(
            apiRepository.getEpisode(characterId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError { showError(it) }
                .subscribe { episode -> _initData.value = episode.mapToView() })
    }
}