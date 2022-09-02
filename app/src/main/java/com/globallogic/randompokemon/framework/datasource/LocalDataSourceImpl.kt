package com.globallogic.randompokemon.framework.datasource

import com.globallogic.core.data.PokemonDataSource
import com.globallogic.core.domain.PokeIndex
import com.globallogic.core.domain.Pokemon
import com.globallogic.randompokemon.adapter.PokemonJsonAdapter
import com.globallogic.randompokemon.data.database.PokemonDao
import com.globallogic.randompokemon.data.database.PokemonDto
import com.globallogic.randompokemon.framework.PrefsCache
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class LocalDataSourceImpl(
    private val prefsCache: PrefsCache,
    private val pokemonDao: PokemonDao,
    private val pokemonJsonAdapter: PokemonJsonAdapter,
) : PokemonDataSource {
    override suspend fun getPokemon(fromCache: Boolean, pokemonId: Int): Flow<Pokemon?> = flow {
        emit(pokemonDao.getPokemon(pokemonId)?.let { pokemonJsonAdapter.parseJson(it.data) })
    }

    override suspend fun getPokeIndex(fromCache: Boolean): Flow<PokeIndex?> = flow {
        emit(prefsCache.getPokeIndex())
    }

    override suspend fun cachePokemon(pokemon: Pokemon): Flow<Any?> = flow {
        pokemonDao.insertPokemon(
            pokemon = PokemonDto(
                id = pokemon.id,
                data = pokemonJsonAdapter.toString(pokemon = pokemon)
            )
        )
        emit(null)
    }

    override fun cachePokeIndex(pokeIndex: PokeIndex): Flow<Any?> = flow {
        prefsCache.savePokeIndex(pokeIndex = pokeIndex)
        emit(null)
    }
}