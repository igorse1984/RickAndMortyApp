package ru.igorsharov.rickandmortyapplication.core.datasource

import androidx.paging.DataSource

class DataSourceFactory<T>(
    private val baseDataSource: BaseDataSource<T>
) : DataSource.Factory<Int, T>() {

    override fun create(): DataSource<Int, T> = baseDataSource
}