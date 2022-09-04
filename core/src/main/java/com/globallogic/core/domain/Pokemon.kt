package com.globallogic.core.domain

/**
 * A class representing a pokemon
 */
data class Pokemon(
    val id: Int,
    val name: String,
    val frontImageUrl: String,
    val backImageUrl: String,
    val moves: List<String>,
    val stats: List<Stat>,
)