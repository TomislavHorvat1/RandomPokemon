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

/**
 * A view model that fetches the pokemon index and pokemon. Used for attempting to get the data from a local source if present. If not, the data it fetched from a remote source.
 *
 * @param getPokemon the use case for getting a pokemon
 * @param cachePokemon the use case for caching a pokemon to local storage
 * @param getPokeIndex the use case for getting the pokemon index
 * @param cachePokeIndex the use case for caching the pokemon index into the shared preferences
 */
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
    private var _pokemon: MutableLiveData<Pokemon> = MutableLiveData()
    val pokemon: LiveData<Pokemon> = _pokemon

    /**
     * Gets the pokemon index from a local or remote source depending on the flag.
     */
    fun getPokeIndex(fromCache: Boolean = true) {
        if (fromCache) getPokeIndexFromCache()
        else getPokeIndexFromRemote()
    }

    /**
     * Gets a random pokemon from a local or remote source depending on the flag.
     */
    fun getRandomPokemon(fromCache: Boolean = true) {
        pokeIndex.value?.let { pokeIndex ->
            if (fromCache) getPokemonFromCache(pokemonId = pokeIndex.getRandomId())
            else getPokemonFromRemote(
                pokemonId = _failedFetchPokemonId.value ?: pokeIndex.getRandomId()
            )
        }
    }

    /**
     * Gets the pokemon index from the shared preferences.
     */
    private fun getPokeIndexFromCache() {
        viewModelScope.launch {
            getPokeIndex.invoke()
                .collect {
                    it?.let {
                        _pokeIndex.value = it
                    } ?: run {
                        _isPokeIndexCached.value = false
                    }
                }
        }
    }

    /**
     * Gets the pokemon index from the API.
     */
    private fun getPokeIndexFromRemote() {
        viewModelScope.launch {
            getPokeIndex.invoke(fromCache = false)
                .catch {
                    getPokeIndex()
                }
                .collect {
                    it?.let { pokeIndex ->
                        _pokeIndex.value = pokeIndex
                        cachePokeIndex.invoke(pokeIndex = pokeIndex)
                            .collect { _isPokeIndexCached.value = true }
                    }
                }
        }
    }

    /**
     * Gets a pokemon from the shared preferences.
     */
    private fun getPokemonFromCache(pokemonId: Int) {
        viewModelScope.launch {
            try {
                getPokemon.invoke(pokemonId = pokemonId)
                    .collect {
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

    /**
     * Gets a pokemon from the API.
     */
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
                    }
                }
            } catch (e: Error) {
                Timber.e(e)
            }
        }
    }

    /**
     * Picks a random id from the pokemon index.
     */
    private fun PokeIndex.getRandomId(): Int =
        indexes[Random().nextInt(count - 1)]
}