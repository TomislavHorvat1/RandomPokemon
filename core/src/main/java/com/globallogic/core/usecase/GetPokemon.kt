package com.globallogic.core.usecase

import com.globallogic.core.data.PokemonDataSource

/**
 * A use case for getting a pokemon.
 */
class GetPokemon(private val dataSource: PokemonDataSource) {
    suspend operator fun invoke(fromCache: Boolean = true, pokemonId: Int) =
        dataSource.getPokemon(fromCache = fromCache, pokemonId = pokemonId)
}