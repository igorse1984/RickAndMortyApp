package ru.igorsharov.rickandmortyapplication.ui.episodes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import ru.igorsharov.rickandmortyapplication.core.api.ApiRepository
import ru.igorsharov.rickandmortyapplication.core.datasource.DataSourceFactory
import ru.igorsharov.rickandmortyapplication.core.platform.BaseViewModel
import ru.igorsharov.rickandmortyapplication.core.platform.SingleLiveEvent
import ru.igorsharov.rickandmortyapplication.ui.entities.EpisodeView
import ru.igorsharov.rickandmortyapplication.ui.episodes.adapter.EpisodesDataSource
import javax.inject.Inject

class EpisodesViewModel @Inject constructor(
    private val apiRepository: ApiRepository
) : BaseViewModel() {

    lateinit var data: LiveData<PagedList<EpisodeView>>
        private set

    private val _loadState = MutableLiveData<Boolean>()
    val loadState: LiveData<Boolean> = _loadState

    private val _openDetail = SingleLiveEvent<Int>()
    val openDetail: LiveData<Int> = _openDetail

    private lateinit var dataSource: EpisodesDataSource

    init {
        initializePaging()
    }

    private fun initializePaging() {
        dataSource = EpisodesDataSource(
            apiRepository,
            compositeDisposable,
            { _loadState.postValue(it) },
            { showError(it) })

        val factory = DataSourceFactory(dataSource)
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(10)
            .build()

        data = LivePagedListBuilder(factory, config).build()
    }

    fun onRefresh() {
        dataSource.invalidate() // TODO
        _loadState.value = false
    }

    fun onItemClicked(position: Int) {
        _openDetail.value = dataSource.listIds[position]
    }
}