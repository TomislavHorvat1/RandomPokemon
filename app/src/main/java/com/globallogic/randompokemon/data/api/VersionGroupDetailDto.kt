package com.globallogic.randompokemon.data.api

import com.globallogic.core.domain.VersionGroupDetail
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class VersionGroupDetailDto(
    val level_learned_at: Long,
    val move_learn_method: BaseDescriptionDto,
    val version_group: BaseDescriptionDto,
) : Dto<VersionGroupDetail> {
    override fun toObject() = VersionGroupDetail(
        levelLearnedAt = level_learned_at,
        moveLearnMethod = move_learn_method.toObject(),
        versionGroup = version_group.toObject(),
    )
}
