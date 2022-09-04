package com.globallogic.core.usecase

import com.globallogic.core.data.PokemonDataSource
import com.globallogic.core.domain.PokeIndex
import kotlinx.coroutines.flow.Flow

/**
 * The use case for caching the pokemon index.
 */
class CachePokeIndex(private val dataSource: PokemonDataSource) {
    operator fun invoke(pokeIndex: PokeIndex): Flow<Any?> =
        dataSource.cachePokeIndex(pokeIndex)
}