package com.globallogic.randompokemon.data.api

import com.globallogic.core.domain.Stat
import com.squareup.moshi.JsonClass
import java.util.*

/**
 * A DTO used for converting a [StatDto] to a [Stat] object
 *
 * @param base_stat the value of the given stat
 * @param effort
 * @param stat the name of the stat
 */
@JsonClass(generateAdapter = true)
data class StatDto(
    val base_stat: Long,
    val effort: Long,
    val stat: BaseDescription,
) : Dto<Stat> {
    override fun toObject() = Stat(
        value = base_stat.toInt(),
        name = stat.name.uppercase(Locale.getDefault()),
    )
}