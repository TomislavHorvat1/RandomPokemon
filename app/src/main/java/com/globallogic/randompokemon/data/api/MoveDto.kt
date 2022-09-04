package com.globallogic.randompokemon.data.api

import com.globallogic.core.domain.Move
import com.squareup.moshi.JsonClass

/**
 * A DTO for converting a [BaseDescription] to a [Move] object
 */
@JsonClass(generateAdapter = true)
data class MoveDto(
    val move: BaseDescription,
) : Dto<Move> {
    override fun toObject() = Move(
        name = move.name,
    )
}
