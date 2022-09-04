package com.globallogic.randompokemon.data.api

import com.squareup.moshi.JsonClass

/**
 * A generalized nested object from the Pokemon API used in several objects
 *
 * @param name a name of the property
 * @param url a URL string
 * */
@JsonClass(generateAdapter = true)
data class BaseDescription(
    val name: String,
    val url: String,
)