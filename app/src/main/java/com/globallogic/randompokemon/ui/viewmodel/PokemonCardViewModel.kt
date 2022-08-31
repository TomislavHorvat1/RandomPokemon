package com.globallogic.randompokemon.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.globallogic.core.usecase.GetPokemon
import com.globallogic.core.usecase.GetPokeIndex
import kotlinx.coroutines.launch
import timber.log.Timber

class PokemonCardViewModel(
    private val getPokemon: GetPokemon,
    private val getPokeIndex: GetPokeIndex
) : ViewModel() {
    fun checkPokeIndex(){
        viewModelScope.launch {
            try {
                getPokeIndex.invoke(false).collect{
                    Timber.d("$it")
                }
            }catch (e: Error){

            }
        }
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

}