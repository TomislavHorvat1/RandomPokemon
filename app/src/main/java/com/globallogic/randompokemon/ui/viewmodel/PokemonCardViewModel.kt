package com.globallogic.randompokemon.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.globallogic.core.usecase.GetPokemon
import kotlinx.coroutines.launch
import timber.log.Timber

class PokemonCardViewModel(
    private val getPokemon: GetPokemon
) : ViewModel() {

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