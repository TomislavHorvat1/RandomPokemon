package com.globallogic.randompokemon.framework.datasource

import com.globallogic.core.data.PokemonDataSource
import com.globallogic.core.domain.PokeIndex
import com.globallogic.core.domain.Pokemon
import com.globallogic.randompokemon.framework.PokemonApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RemoteDataSourceImpl(
    private val api: PokemonApi
) : PokemonDataSource {
    override suspend fun getPokemon(fromCache: Boolean, pokemonId: Int): Flow<Pokemon> = flow {
        emit(api.getPokemon(id = pokemonId).toObject())
    }

    override suspend fun getPokeIndex(fromCache: Boolean): Flow<PokeIndex> = flow {
        emit(api.getPokeIndex().toObject())
    }

    override fun cachePokemon(pokemon: Pokemon) {
        // do nothing
    }

    override fun cachePokeIndex(pokeIndex: PokeIndex) {
        // do nothing
    }
}