package com.globallogic.randompokemon.data.api

import com.globallogic.core.domain.Stat
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class StatDto(
    val base_stat: Long,
    val effort: Long,
    val stat: BaseDescriptionDto,
) : Dto<Stat> {
    override fun toObject() = Stat(
        baseStat = base_stat,
        effort = effort,
        stat = stat.toObject(),
    )
}