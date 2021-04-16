package ru.igorsharov.rickandmortyapplication.core.datasource

import androidx.paging.PageKeyedDataSource

abstract class BaseDataSource<T> : PageKeyedDataSource<Int, T>() {

    protected abstract fun loadOnApi(page: Int, onSuccess: ((List<T>) -> Unit))
    protected var isNextAvailable: Boolean = false
    val listIds = mutableListOf<Int>()

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, T>
    ) {
        loadOnApi(1) { callback.onResult(it, null, 2) }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, T>) {
        val nextPageNo = params.key + 1
        val pageNo = params.key
        if (isNextAvailable) loadOnApi(pageNo) { callback.onResult(it, nextPageNo) }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, T>) {
        val previousPageNo = if (params.key > 1) params.key - 1 else 0
        val pageNo = params.key
        loadOnApi(pageNo) { callback.onResult(it, previousPageNo) }
    }
}