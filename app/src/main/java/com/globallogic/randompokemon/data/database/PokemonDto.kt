package com.globallogic.randompokemon.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * A DTO of the [Pokemon] class for use when saving the object to the Pokemon database.
 *
 * @param id the id of the pokemon
 * @param data the JSON data of the pokemon
 */
@Entity(tableName = "pokemon_table")
class PokemonDto(
    @PrimaryKey val id: Int,
    val data: String,
)