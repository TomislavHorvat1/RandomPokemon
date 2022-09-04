package com.globallogic.core.data

import com.globallogic.core.domain.PokeIndex
import com.globallogic.core.domain.Pokemon
import kotlinx.coroutines.flow.Flow

/**
 * An interface for the data source.
 */
interface PokemonDataSource {
    suspend fun getPokemon(fromCache: Boolean = true, pokemonId: Int): Flow<Pokemon?>
    suspend fun getPokeIndex(fromCache: Boolean = true): Flow<PokeIndex?>
    suspend fun cachePokemon(pokemon: Pokemon): Flow<Any?>
    fun cachePokeIndex(pokeIndex: PokeIndex): Flow<Any?>
}