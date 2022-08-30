package com.globallogic.core.data

import com.globallogic.core.domain.Pokemon
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.single

class PokemonDataRepository private constructor(
    private val localDataSource: PokemonCacheSource,
    private val remoteDataSource: PokemonDataSource,
) : PokemonDataSource {

    companion object {
        fun getInstance(
            localDataSource: PokemonCacheSource,
            remoteDataSource: PokemonDataSource,
        ): PokemonDataRepository = PokemonDataRepository(
            localDataSource = localDataSource,
            remoteDataSource = remoteDataSource,
        )
    }

    var isCacheDirty = true

    override suspend fun getPokemon(pokemonId: Int): Flow<Pokemon> =
        if (isCacheDirty) getPokemonFromServer(pokemonId = pokemonId)
        else getPokemonFromLocalStorage(pokemonId = pokemonId)


    private suspend fun getPokemonFromServer(
        pokemonId: Int,
    ): Flow<Pokemon> {
        val pokemon = remoteDataSource.getPokemon(
            pokemonId = pokemonId,
        )
        localDataSource.savePokemon(pokemon = pokemon.single())

        return pokemon
    }

    private suspend fun getPokemonFromLocalStorage(
        pokemonId: Int,
    ): Flow<Pokemon> =
        localDataSource.getPokemon(
            pokemonId = pokemonId,
        )
}