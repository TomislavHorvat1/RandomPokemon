package com.globallogic.randompokemon.data.api

/**
 * An interface for use with DTOs to convert them to given classes
 */
interface Dto<out I> {
    fun toObject(): I
}