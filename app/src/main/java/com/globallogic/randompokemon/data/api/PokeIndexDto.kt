package com.globallogic.randompokemon.data.api

import com.globallogic.core.domain.PokeIndex
import com.globallogic.randompokemon.BuildConfig
import com.squareup.moshi.JsonClass
import java.util.*

@JsonClass(generateAdapter = true)
data class PokeIndexDto(
    val count: Int,
    val results: List<BaseDescriptionDto>,
) : Dto<PokeIndex> {
    override fun toObject() = PokeIndex(
        count = count,
        indexes = results.map {
            it.url
                .replace("${BuildConfig.API_URL}pokemon", "")
                .replace("/", "")
                .toInt()
        },
        lastRefreshed = Date().time
    )
}