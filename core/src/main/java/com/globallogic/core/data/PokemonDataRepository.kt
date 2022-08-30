package com.globallogic.core.data

import com.globallogic.core.domain.Pokemon

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

    val isCacheDirty = false

    override fun getPokemon(pokemonId: Int, callback: PokemonDataSource.LoadPokemonCallback) {
        if (isCacheDirty) {
            getPokemonFromServer(
                pokemonId = pokemonId,
                callback = callback,
            )
        } else {
            getPokemonFromLocalStorage(
                pokemonId = pokemonId,
                callback = callback,
            )
        }
    }

    private fun getPokemonFromServer(
        pokemonId: Int,
        callback: PokemonDataSource.LoadPokemonCallback
    ) {


        // TODO cache after get
    }

    private fun getPokemonFromLocalStorage(
        pokemonId: Int,
        callback: PokemonDataSource.LoadPokemonCallback,
    ) {

    }
}