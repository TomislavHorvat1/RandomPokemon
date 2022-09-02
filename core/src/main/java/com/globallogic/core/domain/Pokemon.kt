package com.globallogic.core.domain

data class Pokemon(
    val id: Int,
    val moves: List<Move>,
    val name: String,
    val sprites: Sprite,
    val stats: List<Stat>,
)