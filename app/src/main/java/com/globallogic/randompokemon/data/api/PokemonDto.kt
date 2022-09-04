package com.globallogic.randompokemon.data.api

import com.globallogic.core.domain.Pokemon
import com.squareup.moshi.JsonClass
import java.util.*

/**
 * A DTO for converting a [PokemonDto] to a [Pokemon] object.
 *
 * @param id the id of the pokemon
 * @param moves a list of [MoveDto] objects
 * @param name the name of the pokemon
 * @param sprites an object representing the images of the pokemon
 * @param stats a list of [StatDto] objects
 */
@JsonClass(generateAdapter = true)
data class PokemonDto(
    val id: Int,
    val moves: List<MoveDto>,
    val name: String,
    val sprites: Sprite,
    val stats: List<StatDto>,
) : Dto<Pokemon> {
    override fun toObject() = Pokemon(
        id = id,
        name = name.capitalize(),
        frontImageUrl = sprites.front_default,
        backImageUrl = sprites.back_default,
        moves = moves.map {
            it.move.name.capitalize()
        },
        stats = stats.map { it.toObject() },
    )
}

/**
 * Capitalizes the given string
 */
fun String.capitalize(): String = replaceFirstChar {
    if (it.isLowerCase()) it.titlecase(
        Locale.getDefault()
    ) else it.toString()
}