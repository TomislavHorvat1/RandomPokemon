package com.globallogic.randompokemon.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.globallogic.core.usecase.GetPokemon
import com.globallogic.core.usecase.GetPokemonCount
import kotlinx.coroutines.launch
import timber.log.Timber

class PokemonCardViewModel(
    private val getPokemonCount: GetPokemonCount,
    private val getPokemon: GetPokemon
) : ViewModel() {
    fun getPokemonCount() {
        viewModelScope.launch {
            getPokemonCount.invoke().collect {
                Timber.d("$it")
            }
        }
    }

    fun getRandomPokemon() {
        viewModelScope.launch {
            try {
                getPokemon.invoke(1).collect {
                    Timber.d("$it")
                }
            } catch (e: Error) {
                Timber.e(e)
            }
        }
    }

}