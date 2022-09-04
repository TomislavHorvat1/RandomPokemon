package com.globallogic.randompokemon.data.api

import com.squareup.moshi.JsonClass

/**
 * An API object representing a list of urls to sprites.
 *
 * @param front_default a url to an image of the front of the pokemon
 * @param back_default a url to an image of the back of the pokemon
 */
@JsonClass(generateAdapter = true)
data class Sprite(
    val front_default: String,
    val back_default: String,
)