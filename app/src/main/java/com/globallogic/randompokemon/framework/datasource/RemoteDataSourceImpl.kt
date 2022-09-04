package com.globallogic.randompokemon.framework.datasource

import com.globallogic.core.data.PokemonDataSource
import com.globallogic.core.domain.PokeIndex
import com.globallogic.core.domain.Pokemon
import com.globallogic.randompokemon.data.database.PokemonDao
import com.globallogic.randompokemon.data.database.PokemonDto
import com.globallogic.randompokemon.framework.PokemonApi
import kotlinx.coroutines.flow.*

/**
 * An implementation od [PokemonDataSource] that uses the [PokemonApi] as a data source.
 *
 * @property api the API interface
 */
class RemoteDataSourceImpl(
    private val api: PokemonApi,
) : PokemonDataSource {
    /**
     * Gets a [PokemonDto] from the API, creates a [Flow] and emits a [Pokemon] converted from the
     * DTO
     */
    override suspend fun getPokemon(fromCache: Boolean, pokemonId: Int): Flow<Pokemon> = flow {
        emit(api.getPokemon(id = pokemonId).toObject())
    }

    /**
     * Gets a [PokeIndex] object from the API, creates a [Flow] and emits the value
     */
    override suspend fun getPokeIndex(fromCache: Boolean): Flow<PokeIndex> = flow {
        emit(api.getPokeIndex().toObject())
    }

    /**
     * Unused
     */
    override suspend fun cachePokemon(pokemon: Pokemon): Flow<Any?> = flow {
        // do nothing
    }

    /**
     * Unused
     */
    override fun cachePokeIndex(pokeIndex: PokeIndex): Flow<Boolean> = flow {
        // do nothing
    }
}