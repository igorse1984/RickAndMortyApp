package ru.igorsharov.rickandmortyapplication.core.extension

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

fun <T : Any, L : LiveData<T>> LifecycleOwner.observe(liveData: L, body: (T?) -> Unit) {
    liveData.observe(this, Observer(body))
}

fun <T : Any, L : LiveData<T>> LifecycleOwner.observeNotNull(liveData: L, body: (T) -> Unit) {
    liveData.observe(this, Observer(body))
}