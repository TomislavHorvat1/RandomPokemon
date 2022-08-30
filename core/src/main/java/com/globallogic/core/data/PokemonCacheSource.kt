package com.globallogic.core.data

import com.globallogic.core.domain.Pokemon

interface PokemonCacheSource : PokemonDataSource {
    fun savePokemon(pokemon: Pokemon)
    suspend fun setPokemonCount(pokemonCount: Int)
}