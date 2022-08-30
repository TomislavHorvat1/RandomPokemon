package com.globallogic.core.data

import com.globallogic.core.domain.Pokemon
import kotlinx.coroutines.flow.Flow

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

    var isFirstRun = true
    var isCacheDirty = true

    override suspend fun getPokemonCount(): Flow<Int> {
        if (isFirstRun)
            getPokemonCountFromServer().collect {
                localDataSource.setPokemonCount(it)
            }

        return getPokemonCountFromConfig()
    }

    override suspend fun getPokemon(pokemonId: Int): Flow<Pokemon> {
        if (isCacheDirty) {
            getPokemonFromServer(pokemonId = pokemonId).collect {
                localDataSource.savePokemon(it)
            }
        }

        return getPokemonFromLocalStorage(pokemonId = pokemonId)
    }


    private suspend fun getPokemonCountFromServer(): Flow<Int> {
        isFirstRun = false
        return remoteDataSource.getPokemonCount()
    }

    private suspend fun getPokemonCountFromConfig(): Flow<Int> = localDataSource.getPokemonCount()

    private suspend fun getPokemonFromServer(pokemonId: Int): Flow<Pokemon> =
        remoteDataSource.getPokemon(pokemonId = pokemonId)

    private suspend fun getPokemonFromLocalStorage(pokemonId: Int): Flow<Pokemon> =
        localDataSource.getPokemon(pokemonId = pokemonId)
}