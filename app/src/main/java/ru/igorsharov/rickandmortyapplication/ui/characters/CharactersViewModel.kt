package ru.igorsharov.rickandmortyapplication.ui.characters

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import ru.igorsharov.rickandmortyapplication.core.api.ApiRepository
import ru.igorsharov.rickandmortyapplication.core.datasource.DataSourceFactory
import ru.igorsharov.rickandmortyapplication.core.platform.BaseViewModel
import ru.igorsharov.rickandmortyapplication.core.platform.SingleLiveEvent
import ru.igorsharov.rickandmortyapplication.ui.characters.adapter.CharactersDataSource
import ru.igorsharov.rickandmortyapplication.ui.entities.CharacterView
import javax.inject.Inject

class CharactersViewModel @Inject constructor(
    private val apiRepository: ApiRepository
) : BaseViewModel() {

    lateinit var data: LiveData<PagedList<CharacterView>>
        private set

    private val _loadState = MutableLiveData<Boolean>()
    val loadState: LiveData<Boolean> = _loadState

    private val _openDetail = SingleLiveEvent<Int>()
    val openDetail: LiveData<Int> = _openDetail

    private lateinit var dataSource: CharactersDataSource

    init {
        initializePaging()
    }

    private fun initializePaging() {
        dataSource = CharactersDataSource(
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