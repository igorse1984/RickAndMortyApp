package ru.igorsharov.rickandmortyapplication.ui.character

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.igorsharov.rickandmortyapplication.core.api.ApiRepository
import ru.igorsharov.rickandmortyapplication.core.platform.BaseViewModel
import ru.igorsharov.rickandmortyapplication.ui.entities.CharacterView
import ru.igorsharov.rickandmortyapplication.ui.entities.mapToView
import javax.inject.Inject

class CharacterViewModel @Inject constructor(
    private val apiRepository: ApiRepository
) : BaseViewModel() {

    private val _initData = MutableLiveData<CharacterView>().apply { value = CharacterView.empty() }
    val initData: LiveData<CharacterView> = _initData

    fun onViewCreated(characterId: Int) {
        loadApi(characterId)
    }

    private fun loadApi(characterId: Int) {
        compositeDisposable.add(
            apiRepository.getCharacter(characterId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError { showError(it) }
                .subscribe { character -> _initData.value = character.mapToView() })
    }
}