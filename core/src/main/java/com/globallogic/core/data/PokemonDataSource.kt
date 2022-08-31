package com.globallogic.core.data

import com.globallogic.core.domain.PokeIndex
import com.globallogic.core.domain.Pokemon
import kotlinx.coroutines.flow.Flow

interface PokemonDataSource {
    suspend fun getPokemon(fromCache: Boolean = true, pokemonId: Int): Flow<Pokemon>
    suspend fun getPokeIndex(fromCache: Boolean = true): Flow<PokeIndex>
    fun cachePokemon(pokemon: Pokemon)
    fun cachePokeIndex(pokeIndex: PokeIndex)
}