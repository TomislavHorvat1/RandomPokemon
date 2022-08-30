package com.globallogic.randompokemon.framework.datasource

import com.globallogic.core.data.PokemonCacheSource
import com.globallogic.core.domain.Pokemon
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class LocalDataSourceImpl : PokemonCacheSource {
    override suspend fun getPokemon(pokemonId: Int): Flow<Pokemon> = flow {
        emit(Pokemon("local"))
    }

    override fun savePokemon(pokemon: Pokemon) {

    }
}