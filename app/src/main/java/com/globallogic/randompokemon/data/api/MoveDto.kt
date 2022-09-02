package com.globallogic.randompokemon.data.api

import com.globallogic.core.domain.Move
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MoveDto(
    val move: BaseDescriptionDto,
    val version_group_details: List<VersionGroupDetailDto>?,
) : Dto<Move> {
    override fun toObject() = Move(
        move = move.toObject(),
        versionGroupDetail = version_group_details?.map { it.toObject() },
    )
}
