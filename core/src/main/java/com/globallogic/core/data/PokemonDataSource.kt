package com.globallogic.core.data

import com.globallogic.core.domain.Pokemon
import kotlinx.coroutines.flow.Flow

interface PokemonDataSource {
    suspend fun getPokemon(pokemonId: Int): Flow<Pokemon>
}