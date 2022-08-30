package com.globallogic.core.data

import com.globallogic.core.domain.Pokemon

interface PokemonDataSource {
    interface LoadPokemonCallback {
        fun onPokemonLoaded(pokemon: Pokemon)
        fun onError(t: Throwable)
    }

    interface SavePokemonCallback {
        fun onPokemonSaved()
        fun onError(t: Throwable)
    }

    fun getPokemon(pokemonId: Int, callback: LoadPokemonCallback)
}