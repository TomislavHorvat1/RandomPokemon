package com.globallogic.randompokemon.data.api

import com.globallogic.core.domain.PokeIndex
import com.globallogic.randompokemon.BuildConfig
import com.squareup.moshi.JsonClass
import java.util.*

/**
 * A DTO for converting a [PokeIndexDto] to a [PokeIndex] object.
 *
 * @param count a total count of ids present in the database
 * @param results a list of names and urls of all the pokemon in the database
 */
@JsonClass(generateAdapter = true)
data class PokeIndexDto(
    val count: Int,
    val results: List<BaseDescription>,
) : Dto<PokeIndex> {
    override fun toObject() = PokeIndex(
        count = count,
        indexes = results.map { it.url.stripUrlPrefix() },
        lastRefreshed = Date().time
    )
}

/**
 * Strips the URL prefix and trailing slash of the given string and converts it to an [Int]
 */
fun String.stripUrlPrefix(): Int = replace("${BuildConfig.API_URL}pokemon", "")
    .replace("/", "")
    .toInt()