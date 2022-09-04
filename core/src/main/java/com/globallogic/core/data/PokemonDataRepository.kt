package com.globallogic.core.data

import com.globallogic.core.domain.PokeIndex
import com.globallogic.core.domain.Pokemon
import kotlinx.coroutines.flow.Flow

/**
 * A data repository for all data required by the app. The repo handles getting the data from either local or remote repository.
 *
 * @param localDataSource the local data source
 * @param remoteDataSource the remote data source
 */
class PokemonDataRepository private constructor(
    private val localDataSource: PokemonDataSource,
    private val remoteDataSource: PokemonDataSource,
) : PokemonDataSource {

    companion object {
        fun getInstance(
            localDataSource: PokemonDataSource,
            remoteDataSource: PokemonDataSource,
        ): PokemonDataRepository = PokemonDataRepository(
            localDataSource = localDataSource,
            remoteDataSource = remoteDataSource,
        )
    }

    override suspend fun getPokemon(fromCache: Boolean, pokemonId: Int): Flow<Pokemon?> =
        if (fromCache) localDataSource.getPokemon(pokemonId = pokemonId)
        else remoteDataSource.getPokemon(pokemonId = pokemonId)

    override suspend fun getPokeIndex(fromCache: Boolean): Flow<PokeIndex?> =
        if (fromCache) localDataSource.getPokeIndex()
        else remoteDataSource.getPokeIndex()

    override suspend fun cachePokemon(pokemon: Pokemon): Flow<Any?> =
        localDataSource.cachePokemon(pokemon = pokemon)

    override fun cachePokeIndex(pokeIndex: PokeIndex): Flow<Any?> =
        localDataSource.cachePokeIndex(pokeIndex = pokeIndex)
}