package com.globallogic.randompokemon.data.api

import com.globallogic.core.domain.Sprite
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SpriteDto(
    val front_default: String,
    val back_default: String,
) : Dto<Sprite> {
    override fun toObject()= Sprite(
        frontDefault = front_default,
        backDefault = back_default,
    )
}