package com.globallogic.randompokemon.framework.datasource

import com.globallogic.core.data.PokemonDataSource
import com.globallogic.core.domain.PokeIndex
import com.globallogic.core.domain.Pokemon
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class LocalDataSourceImpl() : PokemonDataSource {
    override suspend fun getPokemon(fromCache: Boolean, pokemonId: Int): Flow<Pokemon> =
        flow {
            TODO()
        }

    override suspend fun getPokeIndex(fromCache: Boolean): Flow<PokeIndex> = flow {
        TODO()
    }

    override fun cachePokemon(pokemon: Pokemon) {
        TODO()
    }

    override fun cachePokeIndex(pokeIndex: PokeIndex) {
        TODO("Not yet implemented")
    }
}