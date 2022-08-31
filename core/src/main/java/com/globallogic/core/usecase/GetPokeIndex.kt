package com.globallogic.core.usecase

import com.globallogic.core.data.PokemonDataSource

class GetPokeIndex(private val dataSource: PokemonDataSource) {
    suspend operator fun invoke(fromCache: Boolean = true) =
        dataSource.getPokeIndex(fromCache = fromCache)
}