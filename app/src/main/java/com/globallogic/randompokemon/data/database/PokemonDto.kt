package com.globallogic.randompokemon.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pokemon_table")
class PokemonDto(
    @PrimaryKey val id: Int,
    val data: String,
)