package com.globallogic.randompokemon.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

/**
 * A DAO for accessing the local Pokemon database
 */
@Dao
interface PokemonDao {
    /**
     * Inserts a pokemon into the database
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPokemon(pokemon: PokemonDto)

    /**
     * Gets a pokemon with a given id from the Pokemon database
     */
    @Query("SELECT * FROM pokemon_table WHERE id = :id")
    suspend fun getPokemon(id: Int): PokemonDto?
}