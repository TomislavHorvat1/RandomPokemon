package com.globallogic.randompokemon.framework.datasource

import com.globallogic.core.data.PokemonDataSource
import com.globallogic.core.domain.PokeIndex
import com.globallogic.core.domain.Pokemon
import com.globallogic.randompokemon.framework.PrefsCache
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class LocalDataSourceImpl(private val prefsCache: PrefsCache) : PokemonDataSource {
    override suspend fun getPokemon(fromCache: Boolean, pokemonId: Int): Flow<Pokemon> = flow { }

    override suspend fun getPokeIndex(fromCache: Boolean): Flow<PokeIndex?> = flow {
        emit(prefsCache.getPokeIndex())
    }

    override fun cachePokemon(pokemon: Pokemon) {

    }

    override fun cachePokeIndex(pokeIndex: PokeIndex): Flow<Boolean> = flow {
        prefsCache.savePokeIndex(pokeIndex = pokeIndex)
        emit(true)
    }
}