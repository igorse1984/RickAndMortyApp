package ru.igorsharov.rickandmortyapplication.core.platform

import android.util.Log
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel : ViewModel() {

    protected val compositeDisposable: CompositeDisposable = CompositeDisposable()

    protected fun showError(throwable: Throwable) {
        Log.e(this::class.java.simpleName, "Api response error", throwable)
    }

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }
}