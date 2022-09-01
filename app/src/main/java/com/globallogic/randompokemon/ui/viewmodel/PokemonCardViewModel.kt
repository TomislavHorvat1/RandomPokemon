package com.globallogic.randompokemon.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.globallogic.core.domain.PokeIndex
import com.globallogic.core.usecase.CachePokeIndex
import com.globallogic.core.usecase.GetPokeIndex
import com.globallogic.core.usecase.GetPokemon
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import timber.log.Timber

class PokemonCardViewModel(
    private val getPokemon: GetPokemon,
    private val getPokeIndex: GetPokeIndex,
    private val cachePokeIndex: CachePokeIndex,
) : ViewModel() {

    private val _isPokeIndexCached: MutableLiveData<Boolean> = MutableLiveData(true)
    val isPokeIndexCached = _isPokeIndexCached
    private var _pokeIndex: MutableLiveData<PokeIndex?> = MutableLiveData()
    val pokeIndex: LiveData<PokeIndex?>
        get() = _pokeIndex

    fun checkPokeIndex(fromCache: Boolean = true) {
        if (fromCache) loadPokeIndexFromCache()
        else loadPokeIndexFromRemote()
    }

    fun getRandomPokemon() {
        viewModelScope.launch {
            try {
                getPokemon.invoke(pokemonId = 1).collect {
                    Timber.d(it.name)
                }
            } catch (e: Error) {
                Timber.e(e)
            }
        }
    }

    private fun loadPokeIndexFromCache() {
        viewModelScope.launch {
            getPokeIndex.invoke()
                .catch { }
                .collect {
                    it?.let {
                        _pokeIndex.value = it
                    }?.run {
                        _isPokeIndexCached.value = false
                    }
                }
        }
    }

    private fun loadPokeIndexFromRemote() {
        viewModelScope.launch {
            getPokeIndex.invoke(false)
                .catch {
                    TODO("catch network errors")
                }
                .collect {
                    it?.let { pokeIndex ->
                        _pokeIndex.value = pokeIndex
                        cachePokeIndex.invoke(pokeIndex = pokeIndex)
                            .collect { indexCached ->
                                _isPokeIndexCached.value = indexCached
                            }
                    }?.run {
                        TODO("server is reachable but the index is otherwise unavailable")
                    }
                }
        }
    }
}