package com.globallogic.randompokemon.data.api

import com.globallogic.core.domain.Pokemon
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PokemonDto(
    val id: Int,
    val moves: List<MoveDto>,
    val name: String,
    val sprites: SpriteDto,
    val stats: List<StatDto>,
) : Dto<Pokemon> {
    override fun toObject() = Pokemon(
        id = id,
        moves = moves.map { it.toObject() },
        name = name,
        sprites = sprites.toObject(),
        stats = stats.map { it.toObject() },
    )
}