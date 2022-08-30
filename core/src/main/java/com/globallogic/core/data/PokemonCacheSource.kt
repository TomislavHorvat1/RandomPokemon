package com.globallogic.core.data

import com.globallogic.core.domain.Pokemon

interface PokemonCacheSource : PokemonDataSource {
    interface SavePokemonCallback {
        fun onPokemonSaved()
        fun onError(t: Throwable)
    }

    fun savePokemon(pokemon: Pokemon)
}