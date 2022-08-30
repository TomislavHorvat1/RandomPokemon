package com.globallogic.core.usecase

import com.globallogic.core.data.PokemonDataSource

class GetPokemon(private val dataSource: PokemonDataSource) {

    suspend operator fun invoke(pokemonId: Int) = dataSource.getPokemon(pokemonId)
}