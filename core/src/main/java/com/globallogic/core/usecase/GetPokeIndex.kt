package com.globallogic.core.usecase

import com.globallogic.core.data.PokemonDataSource
import com.globallogic.core.domain.PokeIndex
import kotlinx.coroutines.flow.Flow

/**
 * A use case for getting a pokemon index.
 */
class GetPokeIndex(private val dataSource: PokemonDataSource) {
    suspend operator fun invoke(fromCache: Boolean = true): Flow<PokeIndex?> =
        dataSource.getPokeIndex(fromCache = fromCache)
}