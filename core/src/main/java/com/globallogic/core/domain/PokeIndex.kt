package com.globallogic.core.domain

data class PokeIndex(
    val count: Int,
    val indexes: List<Int>,
    val lastRefreshed: Long,
)
