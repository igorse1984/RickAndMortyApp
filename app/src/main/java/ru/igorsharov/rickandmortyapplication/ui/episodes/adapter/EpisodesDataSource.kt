package ru.igorsharov.rickandmortyapplication.ui.episodes.adapter

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import ru.igorsharov.rickandmortyapplication.core.api.ApiRepository
import ru.igorsharov.rickandmortyapplication.core.datasource.BaseDataSource
import ru.igorsharov.rickandmortyapplication.ui.entities.EpisodeView
import ru.igorsharov.rickandmortyapplication.ui.entities.mapToView

class EpisodesDataSource(
    private val apiRepository: ApiRepository,
    private val compositeDisposable: CompositeDisposable,
    private val isLoad: (Boolean) -> Unit,
    private val onError: (Throwable) -> Unit
) : BaseDataSource<EpisodeView>() {

    override fun loadOnApi(page: Int, onSuccess: ((List<EpisodeView>) -> Unit)) {
        compositeDisposable.add(
            apiRepository.getEpisodes(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { isLoad(true) }
                .doFinally { isLoad(false) }
                .doOnError { onError(it) }
                .subscribe { response ->
                    listIds.addAll(response.results.map { it.id })
                    isNextAvailable = response.info.next != null
                    onSuccess(response.results.map { it.mapToView() })
                })
    }
}