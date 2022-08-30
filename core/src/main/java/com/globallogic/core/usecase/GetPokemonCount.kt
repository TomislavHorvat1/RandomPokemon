package com.globallogic.core.usecase

import com.globallogic.core.data.PokemonDataSource
import kotlinx.coroutines.flow.Flow

class GetPokemonCount(private val dataSource: PokemonDataSource) {
    suspend operator fun invoke(): Flow<Int> = dataSource.getPokemonCount()
}