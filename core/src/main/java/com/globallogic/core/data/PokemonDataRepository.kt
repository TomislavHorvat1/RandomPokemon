package com.globallogic.core.data

import com.globallogic.core.domain.PokeIndex
import com.globallogic.core.domain.Pokemon
import kotlinx.coroutines.flow.Flow

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

    override suspend fun getPokemon(fromCache: Boolean, pokemonId: Int): Flow<Pokemon> =
        if (fromCache) localDataSource.getPokemon(pokemonId = pokemonId)
        else remoteDataSource.getPokemon(pokemonId = pokemonId)

    override suspend fun getPokeIndex(fromCache: Boolean): Flow<PokeIndex?> =
        if (fromCache) localDataSource.getPokeIndex()
        else remoteDataSource.getPokeIndex()

    override fun cachePokemon(pokemon: Pokemon) {
        localDataSource.cachePokemon(pokemon = pokemon)
    }

    override fun cachePokeIndex(pokeIndex: PokeIndex): Flow<Boolean> =
        localDataSource.cachePokeIndex(pokeIndex = pokeIndex)
}