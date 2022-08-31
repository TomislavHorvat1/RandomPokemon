package com.globallogic.core.domain

data class Move(
    val move: BaseDescription,
    val versionGroupDetail: List<VersionGroupDetail>?,
)
