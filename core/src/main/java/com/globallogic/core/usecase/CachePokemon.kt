package com.globallogic.core.usecase

import com.globallogic.core.data.PokemonDataSource
import com.globallogic.core.domain.Pokemon
import kotlinx.coroutines.flow.Flow

class CachePokemon(private val dataSource: PokemonDataSource) {
    suspend operator fun invoke(pokemon: Pokemon): Flow<Any?> =
        dataSource.cachePokemon(pokemon = pokemon)
}