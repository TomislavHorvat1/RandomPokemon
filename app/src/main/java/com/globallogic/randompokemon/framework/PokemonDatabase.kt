package com.globallogic.randompokemon.framework

import androidx.room.Database
import androidx.room.RoomDatabase
import com.globallogic.randompokemon.data.database.PokemonDao
import com.globallogic.randompokemon.data.database.PokemonDto

@Database(entities = [PokemonDto::class], version = 1, exportSchema = false)
abstract class PokemonDatabase : RoomDatabase() {
    companion object {
        const val POKEMON_DB = "pokemon_database"
    }

    abstract fun pokemonDao(): PokemonDao
}