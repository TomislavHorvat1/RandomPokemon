package com.globallogic.randompokemon.framework.datasource

import com.globallogic.core.data.PokemonDataSource
import com.globallogic.core.domain.Pokemon
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RemoteDataSourceImpl : PokemonDataSource {
    override suspend fun getPokemon(pokemonId: Int): Flow<Pokemon> = flow {
        emit(Pokemon("remote"))
    }

    override suspend fun getPokemonCount(): Flow<Int> = flow {
        emit(1234)
    }
}