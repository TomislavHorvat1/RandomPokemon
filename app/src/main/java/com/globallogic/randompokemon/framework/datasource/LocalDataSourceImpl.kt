package com.globallogic.randompokemon.framework.datasource

import com.globallogic.core.data.PokemonDataSource
import com.globallogic.core.domain.Pokemon

class LocalDataSourceImpl: PokemonDataSource {
    override fun getPokemon(pokemonId: Int, callback: PokemonDataSource.LoadPokemonCallback) {
        TODO("Not yet implemented")
    }

    fun putPokemon(pokemon: Pokemon) {
        TODO("Not yet implemented")
    }
}