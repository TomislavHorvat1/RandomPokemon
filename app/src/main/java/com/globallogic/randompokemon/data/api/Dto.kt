package com.globallogic.randompokemon.data.api

interface Dto<out I> {
    fun toObject(): I
}