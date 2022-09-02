package com.globallogic.randompokemon.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PokemonDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPokemon(pokemon: PokemonDto)

    @Query("SELECT * FROM pokemon_table WHERE id = :id")
    suspend fun getPokemon(id: Int): PokemonDto?
}