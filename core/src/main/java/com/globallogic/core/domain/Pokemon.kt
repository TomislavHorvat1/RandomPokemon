package com.globallogic.core.domain

data class Pokemon(
    val moves: List<Move>,
    val name: String,
    val sprites: Sprite,
    val stats: List<Stat>,
)