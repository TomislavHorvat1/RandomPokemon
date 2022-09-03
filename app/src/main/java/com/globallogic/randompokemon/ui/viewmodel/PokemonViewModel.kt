package com.globallogic.randompokemon.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.globallogic.core.domain.PokeIndex
import com.globallogic.core.domain.Pokemon
import com.globallogic.core.usecase.CachePokeIndex
import com.globallogic.core.usecase.CachePokemon
import com.globallogic.core.usecase.GetPokeIndex
import com.globallogic.core.usecase.GetPokemon
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber
import java.util.*

class PokemonViewModel(
    private val getPokemon: GetPokemon,
    private val cachePokemon: CachePokemon,
    private val getPokeIndex: GetPokeIndex,
    private val cachePokeIndex: CachePokeIndex,
) : ViewModel() {

    private val _isPokeIndexCached: MutableLiveData<Boolean> = MutableLiveData(true)
    val isPokeIndexCached: LiveData<Boolean> = _isPokeIndexCached
    private var _pokeIndex: MutableLiveData<PokeIndex?> = MutableLiveData()
    val pokeIndex: LiveData<PokeIndex?> = _pokeIndex
    private val _failedFetchPokemonId: MutableLiveData<Int?> = MutableLiveData(null)
    val failedFetchPokemonId = _failedFetchPokemonId
    private var _pokemon: MutableLiveData<Pokemon?> = MutableLiveData()
    val pokemon: LiveData<Pokemon?> = _pokemon

    fun getPokeIndex(fromCache: Boolean = true) {
        if (fromCache) getPokeIndexFromCache()
        else getPokeIndexFromRemote()
    }

    fun getRandomPokemon(fromCache: Boolean = true) {
        pokeIndex.value?.let { pokeIndex ->
            if (fromCache) getPokemonFromCache(pokemonId = pokeIndex.getRandomId())
            else getPokemonFromRemote(
                pokemonId = _failedFetchPokemonId.value ?: pokeIndex.getRandomId()
            )
        } ?: run {
            TODO("pokemon index is null, the call was made before the index was fetched. This shouldn't happen")
        }
    }

    private fun getPokeIndexFromCache() {
        viewModelScope.launch {
            getPokeIndex.invoke()
                .catch { }
                .collect {
                    it?.let {
                        _pokeIndex.value = it
                    } ?: run {
                        _isPokeIndexCached.value = false
                    }
                }
        }
    }

    private fun getPokeIndexFromRemote() {
        viewModelScope.launch {
            getPokeIndex.invoke(false)
                .catch {
                    TODO("catch network errors")
                }.collect {
                    it?.let { pokeIndex ->
                        _pokeIndex.value = pokeIndex
                        cachePokeIndex.invoke(pokeIndex = pokeIndex)
                            .collect { _isPokeIndexCached.value = true }
                    } ?: run {
                        TODO("server is reachable but the index is otherwise unavailable")
                    }
                }
        }
    }

    private fun getPokemonFromCache(pokemonId: Int) {
        viewModelScope.launch {
            try {
                getPokemon.invoke(pokemonId = pokemonId).collect {
                    it?.let {
                        _pokemon.value = it
                    } ?: run {
                        failedFetchPokemonId.value = pokemonId
                    }
                }
            } catch (e: Error) {
                Timber.e(e)
            }
        }
    }

    private fun getPokemonFromRemote(pokemonId: Int) {
        viewModelScope.launch {
            try {
                getPokemon.invoke(
                    fromCache = false,
                    pokemonId = pokemonId,
                ).catch {
                    // Retry
                    getRandomPokemon()
                }.collect {
                    it?.let {
                        cachePokemon.invoke(pokemon = it).collect()
                        _pokemon.value = it
                        _failedFetchPokemonId.value = null
                    } ?: run {
                        // Retry
                        getRandomPokemon()
                    }
                }
            } catch (e: Error) {
                Timber.e(e)
            }
        }
    }

    private fun PokeIndex.getRandomId(): Int =
        indexes[Random().nextInt(count - 1)]
}