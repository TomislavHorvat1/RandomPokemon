package com.globallogic.randompokemon.framework.datasource

import android.content.Context
import com.globallogic.core.data.PokemonCacheSource
import com.globallogic.core.domain.Pokemon
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class LocalDataSourceImpl(context: Context) : PokemonCacheSource {
    companion object {
        private const val PREFS_FILE = "config"
        private const val POKEMON_COUNT = "pokemon_count"
    }

    private val prefs = context.getSharedPreferences(PREFS_FILE, Context.MODE_PRIVATE)

    override suspend fun getPokemonCount(): Flow<Int> = flow {
        prefs.getInt(POKEMON_COUNT, 1154)
    }

    override suspend fun setPokemonCount(pokemonCount: Int) {
        prefs.edit().putInt(POKEMON_COUNT, pokemonCount).apply()
    }

    override suspend fun getPokemon(pokemonId: Int): Flow<Pokemon> = flow {
        emit(Pokemon("local"))
    }

    override fun savePokemon(pokemon: Pokemon) {

    }
}